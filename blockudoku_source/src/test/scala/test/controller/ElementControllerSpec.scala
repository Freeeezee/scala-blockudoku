package test.controller

import blockudoku.controllers.ElementController
import blockudoku.models.Point
import test.RandomMock
import test.UnitSpec

class ElementControllerSpec extends UnitSpec {
  behavior of "An new ElementController"
      it should "have 3 random elements with points at (1,0) and (0,0)" in {
        val controller = new ElementController(RandomMock())
        controller.elements should not be null
        controller.elements.length should be (3)

        val element = controller.elements(0)
        element.structure should not be null
        element.structure.length should be(2)
        element.structure(0) should equal(Point(1, 0))
        element.structure(1) should equal(Point(0, 0))
    }
      it should "have a new element at slot 0 when regenerate is called on slot 0" in {
        val controller = new ElementController(RandomMock())
        val element = controller.regenerate(0)
        element.structure should not be null
        element.structure.length should be(2)
        element.structure(0) should equal(Point(1, 0))
        element.structure(1) should equal(Point(0, 0))
      }
}