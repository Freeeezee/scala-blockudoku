package test.windows

import test.UnitSpec
import blockudoku.windows.ConsoleWindow

class ConsoleWindowSpec extends UnitSpec {
  "ConsoleWindow" when {
    "initialized with default values" should {
      val window = ConsoleWindow()

      "contain a headline" in {
        window.content should include("Blockudoku_")
      }

      "contain a 'New Game' screen" in {
        pending
      }
    }
  }
}
