package test.views

import blockudoku.views.console.ConsoleHeadlineView
import test.UnitSuite

class ConsoleHeadlineViewSuite extends UnitSuite {
  test("A HeadlineView should throw an IllegalArgumentException if width is less than headline length + 2") {
    assertThrows[IllegalArgumentException] {
      ConsoleHeadlineView(5)
    }
  }
  
  test("A HeadlineView should be scalable in form of '-- Blockudoku_ --' if width is large enough") {
    viewContent(ConsoleHeadlineView(15)) should equal("- Blockudoku_ -\n")

    viewContent(ConsoleHeadlineView(20)) should equal("--- Blockudoku_ ---\n")

    viewContent(ConsoleHeadlineView(30)) should equal("-------- Blockudoku_ --------\n")
  }
}
