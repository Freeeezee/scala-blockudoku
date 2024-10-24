package test.views

import blockudoku.views.console.ConsoleHeadlineView
import test.UnitSpec

class ConsoleHeadlineViewSpec extends UnitSpec {
  object `HeadlineView` {
    object `when the width is less than the headline length + 2` {
      def `should throw an IllegalArgumentException`(): Unit = {
        assertThrows[IllegalArgumentException] {
          ConsoleHeadlineView(5)
        }
      }
    }
    object `when the width is large enough` {
      def `should be scalable in form of '-- Blockudoku_ --'`(): Unit = {
        viewContent(ConsoleHeadlineView(15)) should equal("- Blockudoku_ -\n")

        viewContent(ConsoleHeadlineView(20)) should equal("--- Blockudoku_ ---\n")

        viewContent(ConsoleHeadlineView(30)) should equal("-------- Blockudoku_ --------\n")
      }
    }
  }
}
