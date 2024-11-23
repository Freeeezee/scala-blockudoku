package test.dependencyInjection

import test.UnitSpec

class DependencyInjectionSpec extends UnitSpec {
  override def beforeEach(): Unit = {
    super.beforeEach()
    
    given container: ComponentContainer = new ComponentContainer
  }
  
  "A Dependency Injection container" when {
    "registering components" should {
      "throw an exception if a component of the registered type is already registered" in {
        val container = summon[ComponentContainer]
        
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
        val container = summon[ComponentContainer]
        
        container.register[AImpl](Singleton)
        container.register[B, BImpl](Singleton)
      }
    }
    "being built" should {
      "throw an exception if a required component is not registered" in {
        val container = summon[ComponentContainer]

        container.register[AImpl](Singleton)
        container.register[C](Singleton)

        assertThrows[MissingDependencyException] {
          val provider = container.buildProvider
        }
      }
    }
    "built with all required components registered" should {
      "return a provider that can provide instances of the registered components" in {
        val container = summon[ComponentContainer]

        container.register[A, AImpl](Singleton)
        container.register[B, BImpl](Singleton)
        container.register[C](Singleton)
        container.register[D](Singleton)

        val provider = container.buildProvider

        val d = provider.get[D]
        
        d should not be empty
      }
    }
    "return a provider that can not provide instances of components that are not registered" in {
      val container = summon[ComponentContainer]
      
      container.register[A, AImpl](Singleton)
      
      val provider = container.buildProvider
      
      val b = provider.get[B]
      
      b shouldBe empty
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

class AImpl2 extends A {
  override def doA(): Unit = println("AImpl2.doA")
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
