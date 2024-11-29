package test.windows

import blockudoku.input.ConsoleInputHandler
import blockudoku.windows.ConsoleWindow
import test.UnitSpec

class ConsoleWindowSpec extends UnitSpec {
  "ConsoleWindow" when {
    "initialized with default values" should {
      "contain a headline" in {
        val window = ConsoleWindow(mediator, gridController, elementController, focusManager, ConsoleInputHandler())
        window.content should include("Blockudoku_")
      }

      "contain a 'New Game' screen" in {
        pending
      }
    }
  }
}
