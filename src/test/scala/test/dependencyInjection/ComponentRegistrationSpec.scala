package test.dependencyInjection

import blockudoku.dependencyInjection.ComponentContainer
import blockudoku.dependencyInjection.Lifetime._

class ComponentRegistrationSpec extends DependencyInjectionUnitSpec {
  "A component container when registering components" should {
    "throw an exception if a component of the registered type is already registered" in {
      val container = new ComponentContainer

      container.register[A, AImpl](Singleton)

      assertThrows[IllegalArgumentException] {
        container.register[A, AImpl](Singleton)
      }
      assertThrows[IllegalArgumentException] {
        container.register[A, AImpl2](Singleton)
      }
      assertThrows[IllegalArgumentException] {
        container.register[A](Singleton)
      }
    }

    "be able to register components with or without a corresponding trait" in {
      val container = new ComponentContainer

      container.register[A, AImpl](Singleton)
      container.register[B, BImpl](Singleton)
      container.register[C](Singleton)
      container.register[D](Singleton)
      container.register[E](Singleton)

      ensureProviderWorks(container)
    }

    "be able to register components with factories" in {
      val container = new ComponentContainer

      container.register[A, AImpl](() => new AImpl, Singleton)
      container.register[B, BImpl](() => new BImpl, Singleton)
      container.register[C](Singleton)
      container.register[D](Singleton)
      container.register[E](() => new E, Singleton)

      ensureProviderWorks(container)
    }
  }
}
