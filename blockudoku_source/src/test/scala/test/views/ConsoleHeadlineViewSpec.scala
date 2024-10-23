package test.views

import test.UnitSpec
import blockudoku.views.console.{ConsoleHeadlineView, ConsoleView}
import blockudoku.views.console.composed.RegularConsoleElement

class ConsoleHeadlineViewSpec extends UnitSpec {
  "HeadlineView" should {
    "throw an IllegalArgumentException if the width is less than the headline length + 2" in {
      assertThrows[IllegalArgumentException] {
        ConsoleHeadlineView(5)
      }
    }
    "be scalable in form of '-- Blockudoku_ --'" in {
      val headlineView1 = ConsoleHeadlineView(15)
      val expectedContent1 = "- Blockudoku_ -\n"
      assert(viewContent(headlineView1) == expectedContent1)

      val headlineView2 = ConsoleHeadlineView(20)
      val expectedContent2 = "--- Blockudoku_ ---\n"
      assert(viewContent(headlineView2) == expectedContent2)

      val headlineView3 = ConsoleHeadlineView(30)
      val expectedContent3 = "-------- Blockudoku_ --------\n"
      assert(viewContent(headlineView3) == expectedContent3)
    }
  }
}
