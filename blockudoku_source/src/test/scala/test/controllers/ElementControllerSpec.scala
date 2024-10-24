package test.controllers

import blockudoku.controllers.ElementController
import blockudoku.models.Point
import test.{RandomMock, UnitSpec}

class ElementControllerSpec extends UnitSpec {
  private val controllers = Table(
    "controller",
    new ElementController(RandomMock()),
  )
  
  property("a new ElementController should have 3 random elements with points at (1,0) and (0,0)") {
    forAll(controllers) { controller =>
      controller.elements should not be null
      controller.elements.length should be(3)
      
      val element = controller.elements(0)
      element.structure should not be null
      element.structure.length should be(2)
      element.structure(0) should equal(Point(1, 0))
      element.structure(1) should equal(Point(0, 0))
    }
  }
}
