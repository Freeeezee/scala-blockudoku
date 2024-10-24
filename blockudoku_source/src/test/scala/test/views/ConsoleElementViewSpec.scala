package test.views

import blockudoku.controllers.{ElementController, GridController}
import blockudoku.views.console.ConsoleElementView
import test.{RandomMock, UnitSpec}

class ConsoleElementViewSpec extends UnitSpec {
  private val gridController = GridController(9, 9)
  private val elementController = ElementController(RandomMock())
  private val views = Table(
    ("view", "expected"),
    (ConsoleElementView(gridController, elementController), """----------------- Elements_ -----------------
                                                             |
                                                             |XX               XX               XX
                                                             |""".stripMargin)
  )
  
  property("ElementView should display all elements in ElementController") {
    forAll(views) { (view, expected) =>
      viewContent(view) should be (expected)
    }
  }
}
