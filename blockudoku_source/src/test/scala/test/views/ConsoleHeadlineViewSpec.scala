package test.views

import blockudoku.views.console.ConsoleHeadlineView
import test.UnitSpec

class ConsoleHeadlineViewSpec extends UnitSpec {
  info("Headline view should display the game's headline")
  info("Headline should be displayed as a scaled version of '-- Blockudoku_ --'")
  info("Scaling should be done by adding '-' to the beginning and end of the headline based on width")
  info("Width should be at least headline length + 2, otherwise an IllegalArgumentException should be thrown")

  Feature("Headline view") {
    Scenario("Display a headline for width less than headline length + 2") {
      Given("a width less than headline length + 2")
      val width = 5

      Then("an IllegalArgumentException should be thrown")
      assertThrows[IllegalArgumentException] {
        ConsoleHeadlineView(width)
      }
    }
    Scenario("Display a headline for width large enough") {
      Given("a width large enough")
      val width1 = 15
      val width2 = 20
      val width3 = 30

      Then("the view content should be")
      viewContent(ConsoleHeadlineView(width1)) should equal("- Blockudoku_ -\n")

      viewContent(ConsoleHeadlineView(width2)) should equal("--- Blockudoku_ ---\n")

      viewContent(ConsoleHeadlineView(width3)) should equal("-------- Blockudoku_ --------\n")
    }
  }
}
