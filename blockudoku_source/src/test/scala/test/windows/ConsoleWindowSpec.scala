package test.windows

import blockudoku.controllers.{ElementController, GridController}
import blockudoku.windows.FocusState.Grid
import test.{RandomMock, UnitSpec}
import blockudoku.windows.{ConsoleWindow, FocusManager}

class ConsoleWindowSpec extends UnitSpec {
  "ConsoleWindow" when {
    "initialized with default values" should {
      val focusManager = FocusManager(Grid)
      val window = ConsoleWindow(GridController(9, 9), ElementController(RandomMock(), focusManager), focusManager)

      "contain a headline" in {
        window.content should include("Blockudoku_")
      }

      "contain a 'New Game' screen" in {
        pending
      }
    }
  }
}
