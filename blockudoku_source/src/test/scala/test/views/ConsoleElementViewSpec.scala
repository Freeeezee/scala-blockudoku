package test.views

import blockudoku.controllers.{ElementController, GridController}
import blockudoku.views.console.ConsoleElementView
import org.scalatest.Assertion
import test.{RandomMock, UnitSpec}

class ConsoleElementViewSpec extends UnitSpec {
  object `An ElementView` {
    def `should display all elements in ElementController`: Assertion = {
      val elementController = ElementController(RandomMock())
      val gridController = GridController(9, 9)
      val elementView = ConsoleElementView(gridController, elementController)
      viewContent(elementView).replace("\r\n", "\n") should be ("""----------------- Elements_ -----------------
                                         |
                                         |XX               XX               XX
                                         |""".stripMargin.replace("\r\n", "\n"))
    }
  }
}
