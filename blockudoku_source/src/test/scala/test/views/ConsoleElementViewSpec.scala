package test.views

import test.RandomMock
import blockudoku.controllers.{ElementController, GridController}
import blockudoku.views.console.ConsoleElementView
import test.UnitSpec

class ConsoleElementViewSpec extends UnitSpec {
  behavior of "ElementView"
    it should "display all elements in ElementController" in {
      val elementController = ElementController(RandomMock())
      val gridController = GridController(9, 9)
      val elementView = ConsoleElementView(gridController, elementController)
      viewContent(elementView).replace("\r\n", "\n") should be ("""----------------- Elements_ -----------------
                                                                  |
                                                                  |XX               XX               XX
                                                                  |""".stripMargin.replace("\r\n", "\n"))
    }
}
