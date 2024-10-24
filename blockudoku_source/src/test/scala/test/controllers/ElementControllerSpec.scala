package test.controllers

import blockudoku.controllers.ElementController
import blockudoku.models.Point
import org.junit.Test
import test.{RandomMock, UnitSpec}

class ElementControllerSpec extends UnitSpec {
  @Test
  def elementController_new_have3RandomElementsWithPointsAt1_0And0_0(): Unit = {
    val controller = new ElementController(RandomMock())
    assert(controller.elements != null)
    assert(controller.elements.length == 3)

    val element = controller.elements(0)
    assert(element.structure != null)
    assert(element.structure.length == 2)
    assert(element.structure(0) == Point(1, 0))
    assert(element.structure(1) == Point(0, 0))
  }
  
  @Test
  def elementController_regenerateIsCalledOnSlot0_haveANewElementAtSlot0(): Unit = {
    val controller = new ElementController(RandomMock())
    val element = controller.regenerate(0)
    assert(element.structure != null)
    assert(element.structure.length == 2)
    assert(element.structure(0) == Point(1, 0))
    assert(element.structure(1) == Point(0, 0))
  }
}
