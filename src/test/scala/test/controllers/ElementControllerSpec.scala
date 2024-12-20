package test.controllers

import blockudoku.controllers.mediatorImpl.ElementController
import blockudoku.models.Point
import blockudoku.windows.FocusManager
import blockudoku.windows.focusManagerImpl.FocusState.Elements
import test.{RandomMock, UnitSpec}
class ElementControllerSpec extends UnitSpec {
  "An ElementController" when {
    "new" should {
      "have 3 random elements with points at (1,0) and (0,0)" in {
        val elementController = provider.get[ElementController]
        elementController.elements should not be null
        elementController.elements.length should be (3)

        val element = elementController.elements(0)
        element.structure should not be null
        element.structure.length should be(2)
        element.structure(0) should equal(Point(1, 0))
        element.structure(1) should equal(Point(0, 0))
      }
    }
    "regenerate is called on slot 0" should {
      "have a new element at slot 0" in {
        val elementController = provider.get[ElementController]
        val element = elementController.regenerate(0)
        element.structure should not be null
        element.structure.length should be(2)
        element.structure(0) should equal(Point(1, 0))
        element.structure(1) should equal(Point(0, 0))
      }
    }
  }
}
