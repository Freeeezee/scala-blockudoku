package test.views

import blockudoku.views.console.{ConsoleHeadlineView, ConsoleView}
import blockudoku.views.console.composed.RegularConsoleElement
import test.UnitSpec

class ConsoleHeadlineViewSpec extends UnitSpec {
  behavior of "HeadlineView"
      it should "throw an IllegalArgumentException if the width is less than the headline length + 2" in {
        assertThrows[IllegalArgumentException] {
          ConsoleHeadlineView(5)
        }
      }
      it should "be scalable in form of '-- Blockudoku_ --' if the width is large enough" in {
        viewContent(ConsoleHeadlineView(15)) should equal("- Blockudoku_ -\n")

        viewContent(ConsoleHeadlineView(20)) should equal("--- Blockudoku_ ---\n")

        viewContent(ConsoleHeadlineView(30)) should equal("-------- Blockudoku_ --------\n")
      }
}
