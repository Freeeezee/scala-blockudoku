package test.views

import blockudoku.services.console.ConsoleStyle
import blockudoku.views.console.composed.Direction.*
import blockudoku.views.console.composed.{ComposedConsoleFormatter, Direction, HorizontalFrame, RegularConsoleElement, VerticalFrame}
import test.UnitSpec

class ComposedConsoleFormatterSpec extends UnitSpec {
  private val regular1 = RegularConsoleElement("Regular 1", isInteractable = true)
  private val regular2 = RegularConsoleElement("Regular2", isInteractable = true)

  "A ComposedConsoleFormatter" when {
    "initialized with one regular element" should {
      "show this string if not highlighted" in {
        val formatter = ComposedConsoleFormatter.create(regular1, selectedX = -1, selectedY = -1)

        formatter.content() should be(regular1.content)
      }

      "show this string as highlighted if highlighted" in {
        val formatter = ComposedConsoleFormatter.create(regular1)

        formatter.content() should be(ConsoleStyle.highlighted(regular1.content))
      }

      "throw an exception if trying to get a larger index" in {
        assertThrows[IndexOutOfBoundsException] {
          regular1.get(3)
        }
      }
    }

    "initialized with two regular elements in vertical frame" should {
      val verticalFrame = VerticalFrame(List(regular1, regular2))(0, true)
      val formatter = ComposedConsoleFormatter.create(verticalFrame)

      "show the first element as highlighted if highlighted" in {
        val formatter = ComposedConsoleFormatter.create(verticalFrame)

        formatter.content() should include(ConsoleStyle.highlighted(regular1.content))
        formatter.content() should not include ConsoleStyle.highlighted(regular2.content)
      }

      "show the second element as highlighted if navigated down" in {
        val formatter = ComposedConsoleFormatter.create(verticalFrame).navigate(Down)

        formatter.content() should include(ConsoleStyle.highlighted(regular2.content))
        formatter.content() should not include ConsoleStyle.highlighted(regular1.content)
      }

      "show the second element as highlighted if navigated right" in {
        val formatter = ComposedConsoleFormatter.create(verticalFrame).navigate(Right)

        formatter.content() should include(ConsoleStyle.highlighted(regular2.content))
        formatter.content() should not include ConsoleStyle.highlighted(regular1.content)
      }

      "still show the first element as highlighted if navigated up" in {
        val formatter = ComposedConsoleFormatter.create(verticalFrame).navigate(Up)

        formatter.content() should include(ConsoleStyle.highlighted(regular1.content))
        formatter.content() should not include ConsoleStyle.highlighted(regular2.content)
      }

      "still show the first element as highlighted if navigated left" in {
        val formatter = ComposedConsoleFormatter.create(verticalFrame).navigate(Left)

        formatter.content() should include(ConsoleStyle.highlighted(regular1.content))
        formatter.content() should not include ConsoleStyle.highlighted(regular2.content)
      }

      "show the first element as highlighted if navigated down and then up" in {
        val formatter = ComposedConsoleFormatter.create(verticalFrame).navigate(Down).navigate(Up)

        formatter.content() should include(ConsoleStyle.highlighted(regular1.content))
        formatter.content() should not include ConsoleStyle.highlighted(regular2.content)
      }

      "show the first element as highlighted if navigated right and then left" in {
        val formatter = ComposedConsoleFormatter.create(verticalFrame).navigate(Right).navigate(Left)

        formatter.content() should include(ConsoleStyle.highlighted(regular1.content))
        formatter.content() should not include ConsoleStyle.highlighted(regular2.content)
      }
    }

    "initialized with two regular elements in horizontal frame" should {
      val horizontalFrame = HorizontalFrame(List(regular1, regular2))(0, true)
      val formatter = ComposedConsoleFormatter.create(horizontalFrame)

      "show the first element as highlighted if highlighted" in {
        val formatter = ComposedConsoleFormatter.create(horizontalFrame)

        formatter.content() should include(ConsoleStyle.highlighted(regular1.content))
        formatter.content() should not include ConsoleStyle.highlighted(regular2.content)
      }

      "show the second element as highlighted if navigated right" in {
        val formatter = ComposedConsoleFormatter.create(horizontalFrame).navigate(Right)

        formatter.content() should include(ConsoleStyle.highlighted(regular2.content))
        formatter.content() should not include ConsoleStyle.highlighted(regular1.content)
      }

      "still show the first element as highlighted if navigated down" in {
        val formatter = ComposedConsoleFormatter.create(horizontalFrame).navigate(Down)

        formatter.content() should include(ConsoleStyle.highlighted(regular1.content))
        formatter.content() should not include ConsoleStyle.highlighted(regular2.content)
      }

      "still show the first element as highlighted if navigated up" in {
        val formatter = ComposedConsoleFormatter.create(horizontalFrame).navigate(Up)

        formatter.content() should include(ConsoleStyle.highlighted(regular1.content))
        formatter.content() should not include ConsoleStyle.highlighted(regular2.content)
      }

      "still show the first element as highlighted if navigated left" in {
        val formatter = ComposedConsoleFormatter.create(horizontalFrame).navigate(Left)

        formatter.content() should include(ConsoleStyle.highlighted(regular1.content))
        formatter.content() should not include ConsoleStyle.highlighted(regular2.content)
      }

      "show the first element as highlighted if navigated down and then up" in {
        val formatter = ComposedConsoleFormatter.create(horizontalFrame).navigate(Down).navigate(Up)

        formatter.content() should include(ConsoleStyle.highlighted(regular1.content))
        formatter.content() should not include ConsoleStyle.highlighted(regular2.content)
      }

      "show the first element as highlighted if navigated right and then left" in {
        val formatter = ComposedConsoleFormatter.create(horizontalFrame).navigate(Right).navigate(Left)

        formatter.content() should include(ConsoleStyle.highlighted(regular1.content))
        formatter.content() should not include ConsoleStyle.highlighted(regular2.content)
      }

      "show the second element as highlighted if navigated right thrice" in {
        val formatter = ComposedConsoleFormatter.create(horizontalFrame).navigate(Right).navigate(Right).navigate(Right)

        formatter.content() should include(ConsoleStyle.highlighted(regular2.content))
        formatter.content() should not include ConsoleStyle.highlighted(regular1.content)
      }
    }

    "initialized with two regular elements with different line count in horizontal frame" should {
      "display them correctly" in {
        val regular3 = RegularConsoleElement("Regular 3\nRegular 3", isInteractable = true)
        val regular2 = RegularConsoleElement("Regular2")
        val horizontalFrame = HorizontalFrame(List(regular3, regular2))(0, true)
        val formatter = ComposedConsoleFormatter.create(horizontalFrame, selectedX = -1, selectedY = -1)

        formatter.content() should be("""Regular 3Regular2
                                        |Regular 3""".stripMargin)
      }
    }

    "initialized with three regular elements in nested frames (Vertical - 2, 1)" should {
      val horizontalFrame = HorizontalFrame(List(regular1, regular2))(0, true)
      val verticalFrame = VerticalFrame(List(horizontalFrame, regular1))(0, true)
      val formatter = ComposedConsoleFormatter.create(verticalFrame)

      "show the third element as highlighted if navigated right once and down once" in {
        val formatter = ComposedConsoleFormatter.create(verticalFrame).navigate(Right).navigate(Down)

        formatter.content() should include(ConsoleStyle.highlighted(regular1.content))
        formatter.content() should not include ConsoleStyle.highlighted(regular2.content)
      }
    }
    "initialized with three regular elements in nested frames (Vertical - 1, 2)" should {
      val horizontalFrame = HorizontalFrame(List(regular1, regular2))(0, true)
      val verticalFrame = VerticalFrame(List(regular1, horizontalFrame))(0, true)
      val formatter = ComposedConsoleFormatter.create(verticalFrame)

      "show the first element highlighted if navigated down, right, and up" in {
        val formatter = ComposedConsoleFormatter.create(verticalFrame).navigate(Down).navigate(Right).navigate(Up)

        formatter.content() should include(ConsoleStyle.highlighted(regular1.content))
        formatter.content() should not include ConsoleStyle.highlighted(regular2.content)
      }
    }
  }
}
