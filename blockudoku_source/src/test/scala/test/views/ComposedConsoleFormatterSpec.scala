package test.views

import blockudoku.services.console.ConsoleStyle
import blockudoku.views.console.composed.{ComposedConsoleFormatter, HorizontalFrame, RegularConsoleElement, VerticalFrame}
import test.UnitSpec

class ComposedConsoleFormatterSpec extends UnitSpec {
  private val regular1 = RegularConsoleElement("Regular 1", isInteractable = true)
  private val regular2 = RegularConsoleElement("Regular2", isInteractable = true)

  "A ComposedConsoleFormatter" when {
    "initialized with one regular element" should {
      "show this string if not highlighted" in {
        val formatter = ComposedConsoleFormatter(regular1, selectedX = -1, selectedY = -1)

        formatter.content() should be(regular1.content)
      }

      "show this string as highlighted if highlighted" in {
        val formatter = ComposedConsoleFormatter(regular1)

        formatter.content() should be(ConsoleStyle.highlighted(regular1.content))
      }
    }

    "initialized with two regular elements in vertical frame" should {
      val verticalFrame = VerticalFrame(List(regular1, regular2))(0, true)
      val formatter = ComposedConsoleFormatter(verticalFrame)

      "show the first element as highlighted if highlighted" in {
        val formatter = ComposedConsoleFormatter(verticalFrame)

        formatter.content() should include(ConsoleStyle.highlighted(regular1.content))
        formatter.content() should not include ConsoleStyle.highlighted(regular2.content)
      }

      "show the second element as highlighted if navigated down" in {
        val formatter = ComposedConsoleFormatter(verticalFrame).navigateDown

        formatter.content() should include(ConsoleStyle.highlighted(regular2.content))
        formatter.content() should not include ConsoleStyle.highlighted(regular1.content)
      }

      "show the second element as highlighted if navigated right" in {
        val formatter = ComposedConsoleFormatter(verticalFrame).navigateRight

        formatter.content() should include(ConsoleStyle.highlighted(regular2.content))
        formatter.content() should not include ConsoleStyle.highlighted(regular1.content)
      }

      "still show the first element as highlighted if navigated up" in {
        val formatter = ComposedConsoleFormatter(verticalFrame).navigateUp

        formatter.content() should include(ConsoleStyle.highlighted(regular1.content))
        formatter.content() should not include ConsoleStyle.highlighted(regular2.content)
      }

      "still show the first element as highlighted if navigated left" in {
        val formatter = ComposedConsoleFormatter(verticalFrame).navigateLeft

        formatter.content() should include(ConsoleStyle.highlighted(regular1.content))
        formatter.content() should not include ConsoleStyle.highlighted(regular2.content)
      }

      "show the first element as highlighted if navigated down and then up" in {
        val formatter = ComposedConsoleFormatter(verticalFrame).navigateDown.navigateUp

        formatter.content() should include(ConsoleStyle.highlighted(regular1.content))
        formatter.content() should not include ConsoleStyle.highlighted(regular2.content)
      }

      "show the first element as highlighted if navigated right and then left" in {
        val formatter = ComposedConsoleFormatter(verticalFrame).navigateRight.navigateLeft

        formatter.content() should include(ConsoleStyle.highlighted(regular1.content))
        formatter.content() should not include ConsoleStyle.highlighted(regular2.content)
      }
    }

    "initialized with two regular elements in horizontal frame" should {
      val horizontalFrame = HorizontalFrame(List(regular1, regular2))(0, true)
      val formatter = ComposedConsoleFormatter(horizontalFrame)

      "show the first element as highlighted if highlighted" in {
        val formatter = ComposedConsoleFormatter(horizontalFrame)

        formatter.content() should include(ConsoleStyle.highlighted(regular1.content))
        formatter.content() should not include ConsoleStyle.highlighted(regular2.content)
      }

      "show the second element as highlighted if navigated right" in {
        val formatter = ComposedConsoleFormatter(horizontalFrame).navigateRight

        formatter.content() should include(ConsoleStyle.highlighted(regular2.content))
        formatter.content() should not include ConsoleStyle.highlighted(regular1.content)
      }

      "still show the first element as highlighted if navigated down" in {
        val formatter = ComposedConsoleFormatter(horizontalFrame).navigateDown

        formatter.content() should include(ConsoleStyle.highlighted(regular1.content))
        formatter.content() should not include ConsoleStyle.highlighted(regular2.content)
      }

      "still show the first element as highlighted if navigated up" in {
        val formatter = ComposedConsoleFormatter(horizontalFrame).navigateUp

        formatter.content() should include(ConsoleStyle.highlighted(regular1.content))
        formatter.content() should not include ConsoleStyle.highlighted(regular2.content)
      }

      "still show the first element as highlighted if navigated left" in {
        val formatter = ComposedConsoleFormatter(horizontalFrame).navigateLeft

        formatter.content() should include(ConsoleStyle.highlighted(regular1.content))
        formatter.content() should not include ConsoleStyle.highlighted(regular2.content)
      }

      "show the first element as highlighted if navigated down and then up" in {
        val formatter = ComposedConsoleFormatter(horizontalFrame).navigateDown.navigateUp

        formatter.content() should include(ConsoleStyle.highlighted(regular1.content))
        formatter.content() should not include ConsoleStyle.highlighted(regular2.content)
      }

      "show the first element as highlighted if navigated right and then left" in {
        val formatter = ComposedConsoleFormatter(horizontalFrame).navigateRight.navigateLeft

        formatter.content() should include(ConsoleStyle.highlighted(regular1.content))
        formatter.content() should not include ConsoleStyle.highlighted(regular2.content)
      }

      "show the second element as highlighted if navigated right thrice" in {
        val formatter = ComposedConsoleFormatter(horizontalFrame).navigateRight.navigateRight.navigateRight

        formatter.content() should include(ConsoleStyle.highlighted(regular2.content))
        formatter.content() should not include ConsoleStyle.highlighted(regular1.content)
      }
    }

    "initialized with two regular elements with different line count in horizontal frame" should {
      "display them correctly" in {
        val regular3 = RegularConsoleElement("Regular 3\nRegular 3", isInteractable = true)
        val regular2 = RegularConsoleElement("Regular2")
        val horizontalFrame = HorizontalFrame(List(regular3, regular2))(0, true)
        val formatter = ComposedConsoleFormatter(horizontalFrame, selectedX = -1, selectedY = -1)

        formatter.content() should be("""Regular 3Regular2
                                        |Regular 3""".stripMargin)
      }
    }

    "initialized with three regular elements in nested frames (Vertical - 2, 1)" should {
      val horizontalFrame = HorizontalFrame(List(regular1, regular2))(0, true)
      val verticalFrame = VerticalFrame(List(horizontalFrame, regular1))(0, true)
      val formatter = ComposedConsoleFormatter(verticalFrame)

      "show the third element as highlighted if navigated right once and down once" in {
        val formatter = ComposedConsoleFormatter(verticalFrame).navigateRight.navigateDown

        formatter.content() should include(ConsoleStyle.highlighted(regular1.content))
        formatter.content() should not include ConsoleStyle.highlighted(regular2.content)
      }
    }
    "initialized with three regular elements in nested frames (Vertical - 1, 2)" should {
      val horizontalFrame = HorizontalFrame(List(regular1, regular2))(0, true)
      val verticalFrame = VerticalFrame(List(regular1, horizontalFrame))(0, true)
      val formatter = ComposedConsoleFormatter(verticalFrame)

      "show the first element highlighted if navigated down, right, and up" in {
        val formatter = ComposedConsoleFormatter(verticalFrame).navigateDown.navigateRight.navigateUp

        formatter.content() should include(ConsoleStyle.highlighted(regular1.content))
        formatter.content() should not include ConsoleStyle.highlighted(regular2.content)
      }
    }
  }
}
