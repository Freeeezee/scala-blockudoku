import scala.reflect.ClassTag

enum Lifetime {
  case Singleton
  case Transient
}

case class ComponentDescriptor(traitTag: Option[ClassTag[?]],
                               implTag: ClassTag[?],
                               factory: Option[() => Any],
                               lifetime: Lifetime) {
  val directDependencies: List[ClassTag[?]] = initializeDirectDependencies

  private def initializeDirectDependencies: List[ClassTag[?]] = {
    val constructors = implTag.runtimeClass.getDeclaredConstructors
    implTag.runtimeClass.getDeclaredConstructors.head.getParameterTypes.map(ClassTag(_)).toList
  }
}

class ComponentContainer {
  private var components: Vector[ComponentDescriptor] = Vector.empty
  private var instances: Map[ClassTag[?], Any] = Map.empty

  def register[C: ClassTag](factory: => C, lifetime: Lifetime)(using cc: ClassTag[C]): Unit = {
    components = components :+ ComponentDescriptor(None, cc, Some(() => factory), lifetime)
  }

  def register[C: ClassTag](lifetime: Lifetime)(using cc: ClassTag[C]): Unit = {
    components = components :+ ComponentDescriptor(None, cc, None, lifetime)
  }

  def register[T: ClassTag, C <: T](lifetime: Lifetime)(using ct: ClassTag[T], cc: ClassTag[C]): Unit = {
    components = components :+ ComponentDescriptor(Some(ct), cc, None, lifetime)
  }

  def register[T: ClassTag, C <: T](factory: => C, lifetime: Lifetime)(using ct: ClassTag[T], cc: ClassTag[C]): Unit = {
    components = components :+ ComponentDescriptor(Some(ct), cc, Some(() => factory), lifetime)
  }

  def instance[C: ClassTag]: C = {
    val desc = descriptorFromClassTag[C]
    throwIfDescriptorEmpty(desc)

    instanceFromDescriptor(desc.get).asInstanceOf[C]
  }

  private def descriptorFromClassTag[C](using ct: ClassTag[C]): Option[ComponentDescriptor] = {
    val implDesc = components.find(_.implTag == ct)

    implDesc match {
      case Some(desc) => Some(desc)
      case None => components.find(_.traitTag.contains(ct))
    }
  }

  private def instanceFromDescriptor(descriptor: ComponentDescriptor): Any = {
    descriptor.lifetime match
      case Lifetime.Singleton => singletonInstanceFromDescriptor(descriptor)
      case Lifetime.Transient => newInstanceFromDescriptor(descriptor)
  }

  private def singletonInstanceFromDescriptor(descriptor: ComponentDescriptor): Any = {
    instances.get(descriptor.implTag) match {
      case Some(instance) => instance
      case None =>
        val newInstance = newInstanceFromDescriptor(descriptor)
        instances = instances + (descriptor.implTag -> newInstance)
        newInstance
    }
  }

  private def newInstanceFromDescriptor(descriptor: ComponentDescriptor): Any = {
    descriptor.factory match {
      case Some(factory) => factory()
      case None => newInstanceFromConstructor(descriptor)
    }
  }

  private def newInstanceFromConstructor(descriptor: ComponentDescriptor): Any = {
    val dependencies = collectDependencies(descriptor)

    val constructors = descriptor.implTag.runtimeClass.getDeclaredConstructors

    descriptor.implTag.runtimeClass.getDeclaredConstructors.head.newInstance(dependencies*)
  }

  private def collectDependencies(descriptor: ComponentDescriptor): List[Any] = {
    var dependencies = List.empty[ComponentDescriptor]

    descriptor.directDependencies.foreach { dep =>
      given ct: ClassTag[?] = dep
      val depDesc = descriptorFromClassTag
      throwIfDescriptorEmpty(depDesc, descriptor)

      dependencies = dependencies :+ depDesc.get
    }

    dependencies.map(instanceFromDescriptor)
  }

  private def throwIfDescriptorEmpty(desc: Option[ComponentDescriptor])(using ct: ClassTag[?]): Unit = {
    if (desc.isEmpty) {
      throw new IllegalArgumentException(s"No component registered for ${ct.runtimeClass.getName}")
    }
  }

  private def throwIfDescriptorEmpty(desc: Option[ComponentDescriptor], requiredBy: ComponentDescriptor)
                                    (using ct: ClassTag[?]): Unit = {
    if (desc.isEmpty) {
      throw new IllegalArgumentException(s"No component registered for ${ct.runtimeClass.getName}, " +
        s"required by ${requiredBy.implTag.runtimeClass.getName}")
    }
  }
}

trait A {
  def doA(): Unit
}

trait B {
  def doB(): Unit
}

class AImpl extends A {
  override def doA(): Unit = println("AImpl.doA")
}

class BImpl extends B {
  override def doB(): Unit = println("BImpl.doB")
}

class C(a: A, b: B) {
  def doC(): Unit = {
    a.doA()
    b.doB()
  }
}

class D(c: C) {
  def doD(): Unit = c.doC()
}

val container = new ComponentContainer

container.register[A, AImpl](Lifetime.Singleton)
container.register[B, BImpl](Lifetime.Singleton)
container.register[C](Lifetime.Singleton)
container.register[D](Lifetime.Singleton)

val d = container.instance[D]

d.doD()