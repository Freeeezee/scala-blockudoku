package test.views

import test.{RandomMock, UnitSpec}
import blockudoku.controllers.{ElementController, GridController}
import blockudoku.views.console.ConsoleElementView
import blockudoku.windows.FocusManager
import blockudoku.windows.FocusState.Elements

class ConsoleElementViewSpec extends UnitSpec {
  "ElementView" should {
    "display all elements in ElementController" in {
      val elementController = ElementController(RandomMock(), new FocusManager(Elements))
      val gridController = GridController(9, 9)
      val elementView = ConsoleElementView(gridController, elementController, new FocusManager(Elements))
      viewContent(elementView).replace("\r\n", "\n") should be ("""----------------- Elements_ -----------------
                                         |
                                         |XX               XX               XX
                                         |""".stripMargin.replace("\r\n", "\n"))
    }
  }
}
