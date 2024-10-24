package test.views

import blockudoku.controllers.{ElementController, GridController}
import blockudoku.views.console.ConsoleElementView
import test.{RandomMock, UnitSpec}

class ConsoleElementViewSpec extends UnitSpec {
  info("Element view should display all elements in ElementController")
  info("Elements should be displayed as 'X's")
  
  Feature("Element view") {
    Scenario("Display all elements in ElementController") {
      Given("an ElementController and a GridController")
      val elementController = ElementController(RandomMock())
      val gridController = GridController(9, 9)
      val elementView = ConsoleElementView(gridController, elementController)
      
      Then("the view content should be")
      viewContent(elementView).replace("\r\n", "\n") should be ("""----------------- Elements_ -----------------
                                         |
                                         |XX               XX               XX
                                         |""".stripMargin.replace("\r\n", "\n"))
    }
  }
}
