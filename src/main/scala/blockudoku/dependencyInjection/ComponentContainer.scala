package blockudoku.dependencyInjection

import scala.reflect.ClassTag

/**
 * The dependency injection container.
 * Allows registration of components that can be resolved through the built ComponentProvider later.
 * Components are created lazily when they are first requested.
 * @see [[ComponentProvider]], [[Lifetime]]
 */
class ComponentContainer {
  private var components: Vector[ComponentDescriptor] = Vector.empty

  /**
   * Registers a component with a factory method.
   * @param factory The factory method used to create the component.
   * @param lifetime The lifetime of the component.
   * @tparam C An instantiable component type. 
   * @see [[Lifetime]]
   */
  def register[C: ClassTag](factory: => C, lifetime: Lifetime)(using cc: ClassTag[C]): Unit = {
    ThrowIfExistsAlready[C](using cc)

    components = components :+ ComponentDescriptor(None, cc, Some(() => factory), lifetime)
  }
  
  /**
   * Registers a component.
   * @param lifetime The lifetime of the component.
   * @tparam C An instantiable component type. Must have a no-arg constructor.
   * @see [[Lifetime]]
   */
  def register[C: ClassTag](lifetime: Lifetime)(using cc: ClassTag[C]): Unit = {
    ThrowIfExistsAlready[C](using cc)

    components = components :+ ComponentDescriptor(None, cc, None, lifetime)
  }

  /**
   * Registers a trait and a component derived from it.
   * @param lifetime The lifetime of the component.
   * @tparam T A trait type.
   * @tparam C An instantiable component type derived from the trait. Must have a no-arg constructor.
   * @see [[Lifetime]]
   */
  def register[T: ClassTag, C <: T](lifetime: Lifetime)(using ct: ClassTag[T], cc: ClassTag[C]): Unit = {
    ThrowIfExistsAlready[T, C](using ct, cc)

    components = components :+ ComponentDescriptor(Some(ct), cc, None, lifetime)
  }

  /**
   * Registers a trait and a component derived from it with a factory method.
   *
   * @param factory  The factory method used to create the component.
   * @param lifetime The lifetime of the component.
   * @tparam T A trait type.
   * @tparam C An instantiable component type derived from the trait.
   * @see [[Lifetime]]
   */
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

  /**
   * Builds a ComponentProvider from the registered components.
   * @param checkDependencies (Recommended) If true, checks for each component if all dependencies of the components exist. 
   * @return A ComponentProvider containing the registered components.
   * @throws MissingDependencyException If checkDependencies is true and a component has a dependency that is not registered.
   * @see [[ComponentProvider]]
   */
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