package test.specs.views

import blockudoku.views.console.ConsoleHeadlineView
import test.specs.UnitSpec

class ConsoleHeadlineViewSpec extends UnitSpec {
  "HeadlineView when" >> {
    "the width is less than the headline length + 2" >> {
      "throw an IllegalArgumentException" in {
        ConsoleHeadlineView(5) must throwA[IllegalArgumentException]
      }
    }
    "the width is large enough" >> {
      "be scalable in form of '-- Blockudoku_ --'" in {
        viewContent(ConsoleHeadlineView(15)) must beEqualTo("- Blockudoku_ -\n")

        viewContent(ConsoleHeadlineView(20)) must beEqualTo("--- Blockudoku_ ---\n")

        viewContent(ConsoleHeadlineView(30)) must beEqualTo("-------- Blockudoku_ --------\n")
      }
    }
  }
}
