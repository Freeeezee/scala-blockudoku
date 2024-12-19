package test.windows

import blockudoku.windows.ConsoleWindow
import io.gitlab.freeeezee.yadis.ComponentContainer
import test.UnitSpec
import blockudoku.registerComponents

class ConsoleWindowSpec extends UnitSpec {
  "ConsoleWindow" when {
    "initialized with default values" should {
      "contain a headline" in {
        val window = new ComponentContainer().registerComponents().buildProvider().get[ConsoleWindow]
        window.content should include("Blockudoku_")
      }

      "contain a 'New Game' screen" in {
        pending
      }
    }
  }
}
