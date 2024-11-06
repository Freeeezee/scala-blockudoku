package test.views

import test.UnitSpec
import blockudoku.views.console.{ConsoleHeadlineView, ConsoleView}
import blockudoku.views.console.composed.RegularConsoleElement

class ConsoleHeadlineViewSpec extends UnitSpec {
  "HeadlineView" when {
    "the width is less than the headline length + 2" should {
      "throw an IllegalArgumentException" in {
        assertThrows[IllegalArgumentException] {
          ConsoleHeadlineView(5)
        }
      }
    }
    "the width is large enough" should {
      "be scalable in form of '-- Blockudoku_ --'" in {
        viewContent(ConsoleHeadlineView(15)) should equal("- Blockudoku_ -\n")

        viewContent(ConsoleHeadlineView(20)) should equal("--- Blockudoku_ ---\n")

        viewContent(ConsoleHeadlineView(30)) should equal("-------- Blockudoku_ --------\n")
      }
    }
  }
}
