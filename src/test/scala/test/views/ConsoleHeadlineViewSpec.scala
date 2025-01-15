package test.views

import blockudoku.views.console.ConsoleHeadlineView
import blockudoku.windows.FocusManager

class ConsoleHeadlineViewSpec extends ViewSpec {
  "HeadlineView" when {
    "the width is less than the headline length + 2" should {
      "throw an IllegalArgumentException" in {
        assertThrows[IllegalArgumentException] {
          ConsoleHeadlineView(5, provider.get[FocusManager], provider.get[WindowMock])
        }
      }
    }
    "the width is large enough" should {
      "be scalable in form of '-- Blockudoku_ --'" in {
        viewContent(ConsoleHeadlineView(15, provider.get[FocusManager], provider.get[WindowMock])) should equal("- Blockudoku_ -\n")

        viewContent(ConsoleHeadlineView(20, provider.get[FocusManager], provider.get[WindowMock])) should equal("--- Blockudoku_ ---\n")

        viewContent(ConsoleHeadlineView(30, provider.get[FocusManager], provider.get[WindowMock])) should equal("-------- Blockudoku_ --------\n")
      }
    }
  }
}
