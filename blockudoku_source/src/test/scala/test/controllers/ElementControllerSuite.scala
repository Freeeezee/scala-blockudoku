package test.controllers

import blockudoku.controllers.ElementController
import blockudoku.models.Point
import test.{RandomMock, UnitSuite}

class ElementControllerSuite extends UnitSuite {
  test("A new element controller should have 3 random elements with points at (1,0) and (0,0)") {
    val controller = new ElementController(RandomMock())
    
    controller.elements should not be null
    controller.elements.length should be(3)
    
    val element = controller.elements(0)
    element.structure should not be null
    element.structure.length should be(2)
    element.structure(0) should be(Point(1, 0))
    element.structure(1) should be(Point(0, 0))
  }
}
