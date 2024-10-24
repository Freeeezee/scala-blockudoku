package test.views

import blockudoku.views.console.ConsoleHeadlineView
import test.UnitSpec

class ConsoleHeadlineViewSpec extends UnitSpec {
  private val views = Table(
    ("width", "expected"),
    (5, "error"),
    (15, "- Blockudoku_ -\n"),
    (20, "--- Blockudoku_ ---\n"),
    (30, "-------- Blockudoku_ --------\n")
  )

  property("HeadlineView should throw an IllegalArgumentException when the width is less than the headline length + 2") {
    forAll(views) { (width, expected) =>
      if (expected == "error") {
        assertThrows[IllegalArgumentException] {
          ConsoleHeadlineView(width)
        }
      }
    }
  }
  
  property("HeadlineView should be scalable in form of '-- Blockudoku_ --'") {
    forAll(views) { (width, expected) =>
      if (expected != "error") {
        viewContent(ConsoleHeadlineView(width)) should equal(expected)
      }
    }
  }
}
