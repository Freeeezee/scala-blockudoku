package test.dependencyInjection

import scala.reflect.ClassTag
import blockudoku.dependencyInjection.{ComponentContainer, MissingDependencyException}
import blockudoku.dependencyInjection.Lifetime.*

class ProviderSpec extends DependencyInjectionUnitSpec {
  "A component provider" when {
    "built with all required components registered" should {
      "throw an exception if components that are not registered are requested" in {
        val container = new ComponentContainer

        container.register[A, AImpl](Singleton)

        val provider = container.buildProvider()

        assertThrows[IllegalArgumentException] {
          val b = provider.get[B]
        }
      }
      "create a new instance for transient components on each request" in {
        val container = new ComponentContainer

        container.register[ChangingClass](Transient)

        val provider = container.buildProvider()

        val changing1 = provider.get[ChangingClass]
        val changing2 = provider.get[ChangingClass]

        changing1.value should not be changing2.value
      }
      "create a single instance for singleton components" in {
        val container = new ComponentContainer

        container.register[A, AImpl](Singleton)

        val provider = container.buildProvider()

        val singleton1 = provider.get[A]
        val singleton2 = provider.get[A]

        singleton1 should be theSameInstanceAs singleton2
      }
    }
    "built with not all required components registered" should {
      "thrown an exception when trying to get an instance with missing dependency" in {
        val container = new ComponentContainer

        container.register[A, AImpl](Singleton)
        container.register[C](Singleton)

        val provider = container.buildProvider(checkDependencies = false)

        assertThrows[MissingDependencyException[C, B]] {
          val c = provider.get[C]
        }
      }
    }
  }
  "A MissingDependencyException" should {
    "contain the name of the missing dependency and the type that requires it" in {
      val exception = new MissingDependencyException[C, B]

      exception.getMessage should be(s"Missing dependency ${classOf[B].getName} for type ${classOf[C].getName}")
    }
  }
}
