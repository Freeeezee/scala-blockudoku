package test.specs.controllers

import blockudoku.controllers.ElementController
import blockudoku.models.Point
import test.RandomMock
import test.specs.UnitSpec

class ElementControllerSpec extends UnitSpec {
  "An ElementController when" >> {
    "new should" >> {
      "have 3 random elements with points at (1,0) and (0,0)" >> {
        val controller = new ElementController(RandomMock())
        controller.elements.length should beEqualTo(3)

        val element = controller.elements(0)
        element.structure.length should beEqualTo(2)
        element.structure(0) should beEqualTo(Point(1, 0))
        element.structure(1) should beEqualTo(Point(0, 0))
      }
    }
    "regenerate is called on slot 0 should" >> {
      "have a new element at slot 0" >> {
        val controller = new ElementController(RandomMock())
        val element = controller.regenerate(0)
        element.structure.length should beEqualTo(2)
        element.structure(0) should beEqualTo(Point(1, 0))
        element.structure(1) should beEqualTo(Point(0, 0))
      }
    }
  }
}
