package blockudoku.dependencyInjection

import java.lang.reflect.Constructor
import scala.reflect.ClassTag

/**
 * The actual injector. Creates instances of components and manages their lifetimes.
 * @param components The components to be managed by the injector.
 * @see [[ComponentContainer]], [[Lifetime]]
 */
class ComponentProvider(private val components: Vector[ComponentDescriptor]) {
  private val componentFactories: Map[ComponentDescriptor, Any => Any] = initializeComponentFactories
  private var instances: Map[ClassTag[?], Any] = Map.empty

  private def initializeComponentFactories: Map[ComponentDescriptor, Any => Any] = {
    components.map { desc =>
      desc -> factoryMethodFromDescriptor(desc)
    }.toMap
  }
  
  private def factoryMethodFromDescriptor(descriptor: ComponentDescriptor): Any => Any = {
    descriptor.factory match {
      case Some(factory) => factory.asInstanceOf[Any => Any]
      case None => 
        val constructors = descriptor.implTag.runtimeClass.getDeclaredConstructors

        wrapConstructor(constructors.head)
    }
  }

  private def wrapConstructor(constructor: Constructor[?]): Any => Any = {
    case args: Seq[_] => constructor.newInstance(args.map(_.asInstanceOf[Object])*)
    case arg => constructor.newInstance(arg.asInstanceOf[Object])
  }

  /**
   * Gets an instance of a component.
   * @tparam C The type of the component to get.
   * @return An instance of the component.
   * @throws IllegalArgumentException If no component is registered for the given type.
   */
  def get[C: ClassTag]: C = {
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
    val dependencies = collectDependencies(descriptor)

    val factory = componentFactories(descriptor)
    
    factory(dependencies)
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
      throw new MissingDependencyException(using requiredBy.implTag, ct)
    }
  }
}
