package test.views

import blockudoku.controllers.{ElementController, GridController}
import blockudoku.views.console.ConsoleElementView
import test.{RandomMock, UnitSuite}

class ConsoleElementViewSuite extends UnitSuite {
  test("ElementView should display all elements in ElementController") {
    val elementController = ElementController(RandomMock())
    val gridController = GridController(9, 9)
    val elementView = ConsoleElementView(gridController, elementController)
    
    val expected = """----------------- Elements_ -----------------
                     |
                     |XX               XX               XX
                     |""".stripMargin
    
    viewContent(elementView) should be(expected)
  }
}
