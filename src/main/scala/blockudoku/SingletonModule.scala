package blockudoku
import com.google.inject
import com.google.inject.Scope

import java.lang.annotation.Annotation
import com.google.inject.{AbstractModule, Scopes}
import net.codingwell.scalaguice.ScalaModule

abstract class SingletonModule extends AbstractModule with ScalaModule {

  def bindSingleTon[T](clazz: Class[T]): Unit = {
    bind(clazz).in(Scopes.SINGLETON)
  }

  def bindSingleton[T, I <: T](interface: Class[T], implementation: Class[I]): Unit = {
    bind(interface).to(implementation).in(Scopes.SINGLETON)
  }
}
