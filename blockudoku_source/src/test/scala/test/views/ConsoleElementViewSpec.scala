package test.views

import test.{RandomMock, UnitSpec}
import blockudoku.views.ConsoleElementView
import blockudoku.controllers.ElementController

class ConsoleElementViewSpec extends UnitSpec {
  "ElementView" should {
    "display all elements in ElementController" in {
      val elementController = ElementController(RandomMock())
      val elementView = ConsoleElementView(elementController.maxElementLength, elementController.elements, width = 50)
      elementView.content() should be ("""------------------- Elements_ -------------------
                                         |
                                         |xx                xx                xx
                                         |
                                         |
                                         |
                                         |0                 1                 2 """.stripMargin)
    }
  }
}
