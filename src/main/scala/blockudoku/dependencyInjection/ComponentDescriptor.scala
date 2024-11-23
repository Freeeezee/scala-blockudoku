package blockudoku.dependencyInjection

import scala.reflect.ClassTag

case class ComponentDescriptor(traitTag: Option[ClassTag[?]],
                               implTag: ClassTag[?],
                               factory: Option[() => Any],
                               lifetime: Lifetime) {
  val directDependencies: List[ClassTag[?]] = initializeDirectDependencies

  private def initializeDirectDependencies: List[ClassTag[?]] = {
    val constructors = implTag.runtimeClass.getDeclaredConstructors
    
    if (constructors.isEmpty) {
      throw new IllegalArgumentException(s"Component is a trait, abstract or void class: ${implTag.runtimeClass.getName}")
    }
    
    constructors.head.getParameterTypes.map(ClassTag(_)).toList
  }
}