package test.views

import blockudoku.views.console.{ConsoleHeadlineView, ConsoleView}
import blockudoku.views.console.composed.RegularConsoleElement
import test.UnitSpec
class ConsoleHeadlineViewSpec extends UnitSpec {
  describe("HeadlineView") {
    describe("when the width is less than the headline length + 2") {
      it("should throw an IllegalArgumentException") {
        assertThrows[IllegalArgumentException] {
          ConsoleHeadlineView(5)
        }
      }
    }
    describe("when the width is large enough") {
      it("should be scalable in form of '-- Blockudoku_ --'") {
        viewContent(ConsoleHeadlineView(15)) should equal("- Blockudoku_ -\n")

        viewContent(ConsoleHeadlineView(20)) should equal("--- Blockudoku_ ---\n")

        viewContent(ConsoleHeadlineView(30)) should equal("-------- Blockudoku_ --------\n")
      }
    }
  }
}
