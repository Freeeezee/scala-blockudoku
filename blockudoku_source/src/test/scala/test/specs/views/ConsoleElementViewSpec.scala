package test.specs.views

import blockudoku.controllers.{ElementController, GridController}
import blockudoku.views.console.ConsoleElementView
import test.RandomMock
import test.specs.UnitSpec

class ConsoleElementViewSpec extends UnitSpec {
  "ElementView should" >> {
    "display all elements in ElementController" >> {
      val elementController = ElementController(RandomMock())
      val gridController = GridController(9, 9)
      val elementView = ConsoleElementView(gridController, elementController)
      val expected = """----------------- Elements_ -----------------
          |
          |XX               XX               XX
          |""".stripMargin

      viewContent(elementView) must beEqualTo(expected)
    }
  }
}
