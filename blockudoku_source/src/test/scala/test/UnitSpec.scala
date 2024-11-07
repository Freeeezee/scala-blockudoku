package test

import blockudoku.controllers.{ElementController, GridController}
import blockudoku.views.console.ConsoleView
import blockudoku.windows.FocusManager
import blockudoku.windows.FocusState.Elements
import org.scalatest.{BeforeAndAfterEach, GivenWhenThen}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import scala.compiletime.uninitialized

abstract class UnitSpec extends AnyWordSpec with Matchers with GivenWhenThen with BeforeAndAfterEach {
  protected var focusManager: FocusManager = uninitialized
  protected var elementController: ElementController = uninitialized
  protected var gridController: GridController = uninitialized

  override def beforeEach(): Unit = {
    super.beforeEach()

    focusManager = FocusManager(Elements)
    elementController = ElementController(RandomMock(), focusManager)
    gridController = GridController(9, 9, elementController, focusManager)
  }

  def viewContent(view: ConsoleView): String = {
    view.consoleElement.content(-1)
  }
}
