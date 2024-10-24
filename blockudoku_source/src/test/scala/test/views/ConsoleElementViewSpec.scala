package test.views

import blockudoku.controllers.{ElementController, GridController}
import blockudoku.views.console.ConsoleElementView
import org.junit.Test
import test.{RandomMock, UnitSpec}

class ConsoleElementViewSpec extends UnitSpec {
  @Test
  def elementView_displayAllElementsInElementController(): Unit = {
    val elementController = ElementController(RandomMock())
    val gridController = GridController(9, 9)
    val elementView = ConsoleElementView(gridController, elementController)
    
    val expected = """----------------- Elements_ -----------------
                      |
                      |XX               XX               XX
                      |""".stripMargin
    assert(viewContent(elementView) == expected) 
  }
}
