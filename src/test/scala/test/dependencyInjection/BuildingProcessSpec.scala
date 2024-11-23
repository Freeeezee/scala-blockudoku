package test.dependencyInjection

import scala.annotation.nowarn
import blockudoku.dependencyInjection.{ComponentContainer, MissingDependencyException}
import blockudoku.dependencyInjection.Lifetime.*

class BuildingProcessSpec extends DependencyInjectionUnitSpec {
  "A component container being built" should {
    "throw an exception if a required component is not registered and using dependency check" in {
      val container = new ComponentContainer

      container.register[A, AImpl](Singleton)
      container.register[C](Singleton)

      assertThrows[MissingDependencyException[C, B]] {
        val provider = container.buildProvider(checkDependencies = true: @nowarn)
      }
    }
    "not throw an exception if a required component is not registered and not using dependency check" in {
      val container = new ComponentContainer

      container.register[A, AImpl](Singleton)
      container.register[C](Singleton)

      val provider = container.buildProvider(checkDependencies = false)
    }
  }
}
