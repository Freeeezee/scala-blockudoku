package test.views

import blockudoku.controllers.mediatorImpl.ElementController
import blockudoku.views.console.ConsoleElementView
import blockudoku.views.console.composed.ComposedConsoleFormatter

class ConsoleElementViewSpec extends ViewSpec {
  "ElementView" should {
    "display all elements in ElementController" in {
      
      val elementView = provider.get[ConsoleElementView]
      viewContent(elementView).replace("\r\n", "\n") should be ("""----------------- Elements_ -----------------
                                         |
                                         |XX               XX               XX
                                         |""".stripMargin.replace("\r\n", "\n"))
    }

    "select the highlighted element in ElementController" in {
      val elementView = provider.get[ConsoleElementView]
      val formatter = ComposedConsoleFormatter.create(elementView.consoleElement)

      formatter.select()
      
      val elementController = provider.get[ElementController]

      elementController.selectedElement should be (Some(elementController.elements.head))
    }
  }
}
