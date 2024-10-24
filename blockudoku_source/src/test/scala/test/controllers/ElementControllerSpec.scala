package test.controllers

import test.UnitSpec

class ElementControllerSpec extends UnitSpec {
  object `An ElementController` {
    object `when new` {
      def `should have 3 random elements with points at (1,0) and (0,0)`: Unit = {
        val controller = new blockudoku.controllers.ElementController(test.RandomMock())
        controller.elements should not be null
        controller.elements.length should be (3)

        val element = controller.elements(0)
        element.structure should not be null
        element.structure.length should be(2)
        element.structure(0) should equal(blockudoku.models.Point(1, 0))
        element.structure(1) should equal(blockudoku.models.Point(0, 0))
      }
    }
    object `regenerate is called on slot 0` {
      def `should have a new element at slot 0`: Unit = {
        val controller = new blockudoku.controllers.ElementController(test.RandomMock())
        val element = controller.regenerate(0)
        element.structure should not be null
        element.structure.length should be(2)
        element.structure(0) should equal(blockudoku.models.Point(1, 0))
        element.structure(1) should equal(blockudoku.models.Point(0, 0))
      }
    }
  }
}
