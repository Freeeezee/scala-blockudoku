package test.dependencyInjection

import blockudoku.dependencyInjection.ComponentContainer
import test.UnitSpec

class DependencyInjectionUnitSpec extends UnitSpec {
  protected def ensureProviderWorks(componentContainer: ComponentContainer): Unit = {
    val provider = componentContainer.buildProvider()

    val a = provider.get[A]
    val b = provider.get[B]
    val c = provider.get[C]
    val d = provider.get[D]
    val e = provider.get[E]

    a.getA should be("AImpl.doA")
    b.getB should be("BImpl.doB")
    c.getC should be("AImpl.doA BImpl.doB")
    d.getD should be("AImpl.doA BImpl.doB")
  }
}
