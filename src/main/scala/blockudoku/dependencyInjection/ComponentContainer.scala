package blockudoku.dependencyInjection

import scala.reflect.ClassTag

class ComponentContainer {
  private var components: Vector[ComponentDescriptor] = Vector.empty

  def register[C: ClassTag](factory: => C, lifetime: Lifetime)(using cc: ClassTag[C]): Unit = {
    ThrowIfExistsAlready[C](using cc)

    components = components :+ ComponentDescriptor(None, cc, Some(() => factory), lifetime)
  }

  def register[C: ClassTag](lifetime: Lifetime)(using cc: ClassTag[C]): Unit = {
    ThrowIfExistsAlready[C](using cc)

    components = components :+ ComponentDescriptor(None, cc, None, lifetime)
  }

  def register[T: ClassTag, C <: T](lifetime: Lifetime)(using ct: ClassTag[T], cc: ClassTag[C]): Unit = {
    ThrowIfExistsAlready[T, C](using ct, cc)

    components = components :+ ComponentDescriptor(Some(ct), cc, None, lifetime)
  }

  def register[T: ClassTag, C <: T](factory: => C, lifetime: Lifetime)(using ct: ClassTag[T], cc: ClassTag[C]): Unit = {
    ThrowIfExistsAlready[T, C](using ct, cc)

    components = components :+ ComponentDescriptor(Some(ct), cc, Some(() => factory), lifetime)
  }

  private def ThrowIfExistsAlready[C](using cc: ClassTag[C]): Unit = {
    if (components.exists(_.implTag == cc)) {
      throw new IllegalArgumentException(s"A component of type ${cc.runtimeClass.getName} is already registered")
    }
  }
  private def ThrowIfExistsAlready[T, C](using ct: ClassTag[T], cc: ClassTag[C]): Unit = {
    ThrowIfExistsAlready[C]

    if (components.exists(_.traitTag.exists(_.runtimeClass == ct.runtimeClass))) {
      throw new IllegalArgumentException(s"A component of type ${ct.runtimeClass.getName} is already registered")
    }
  }

  def buildProvider(checkDependencies: Boolean = true): ComponentProvider = {
    if (checkDependencies) {
      ensureAllDependenciesExist()
    }

    new ComponentProvider(components)
  }

  private def ensureAllDependenciesExist(): Unit = {
    components.foreach(ensureComponentDependenciesExist)
  }

  private def ensureComponentDependenciesExist(descriptor: ComponentDescriptor): Unit = {
    descriptor.directDependencies.foreach { dependency =>
      if (!implementationExists(using dependency) && !traitExists(using dependency)) {
        throw MissingDependencyException(using descriptor.implTag, dependency)
      }
    }
  }

  private def implementationExists[T](using ct: ClassTag[T]): Boolean = {
    components.exists(_.implTag.runtimeClass == ct.runtimeClass)
  }

  private def traitExists[T](using ct: ClassTag[T]): Boolean = {
    components.exists(_.traitTag.exists(_.runtimeClass == ct.runtimeClass))
  }
}