package blockudoku.dependencyInjection

import scala.reflect.ClassTag

class MissingDependencyException[T, D](using clazz: ClassTag[T], dependency: ClassTag[D]) extends Exception {
  override def getMessage: String = s"Missing dependency ${dependency.runtimeClass.getName} " +
    s"for type ${clazz.runtimeClass.getName}"
}
