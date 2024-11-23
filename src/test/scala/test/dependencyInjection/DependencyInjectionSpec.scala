package test.dependencyInjection

import blockudoku.dependencyInjection.{ComponentContainer, MissingDependencyException}
import blockudoku.dependencyInjection.Lifetime.Singleton
import test.UnitSpec

class DependencyInjectionSpec extends UnitSpec {
  "A Dependency Injection container" when {
    "registering components" should {
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
      
      "register components with or without a corresponding trait" in {
        val container = new ComponentContainer
        
        container.register[AImpl](Singleton)
        container.register[B, BImpl](Singleton)
      }
    }
    "being built" should {
      "throw an exception if a required component is not registered and using dependency check" in {
        val container = new ComponentContainer

        container.register[A, AImpl](Singleton)
        container.register[C](Singleton)

        assertThrows[MissingDependencyException[C, B]] {
          val provider = container.buildProvider(checkDependencies = true)
        }
      }
      "not throw an exception if a required component is not registered and not using dependency check" in {
        val container = new ComponentContainer

        container.register[A, AImpl](Singleton)
        container.register[C](Singleton)

        val provider = container.buildProvider(checkDependencies = false)
      }
    }
    "built with all required components registered" should {
      "return a provider that can provide instances of the registered components" in {
        val container = new ComponentContainer

        container.register[A, AImpl](Singleton)
        container.register[B, BImpl](Singleton)
        container.register[C](Singleton)
        container.register[D](Singleton)

        val provider = container.buildProvider()

        val d = provider.get[D]

        d.getD shouldBe "AImpl.doA BImpl.doB"
      }
    }
    "return a provider that can not provide instances of components that are not registered" in {
      val container = new ComponentContainer

      container.register[A, AImpl](Singleton)
      
      val provider = container.buildProvider()

      assertThrows[IllegalArgumentException] {
        val b = provider.get[B]
      }
    }
    "built without all required components registered" should {
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
}

trait A {
  def getA: String
}

trait B {
  def getB: String
}

class AImpl extends A {
  override def getA: String = "AImpl.doA"
}

class AImpl2 extends A {
  override def getA: String = "AImpl2.doA"
}

class BImpl extends B {
  override def getB: String = "BImpl.doB"
}

class C(a: A, b: B) {
  def getC: String = {
    s"${a.getA} ${b.getB}"
  }
}

class D(c: C) {
  def getD: String = c.getC
}
