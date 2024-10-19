package blockudoku.windows

import blockudoku.views.ComposedConsoleView
import blockudoku.views.composed.{HorizontalFrame, RegularConsoleElement, VerticalFrame}
import org.jline.terminal.{Terminal, TerminalBuilder}
import org.jline.utils.NonBlockingReader

class TestWindow extends Window {
  private var view = initializeView

  private def initializeView: ComposedConsoleView = {
    val content0 = "This is the first test content."
    val content1 = "This is a\nmultiline\ncontent."
    val content2 = "This is another\nmultiline content."
    val content3 = "This\nis the\nlast multiline\ncontent."
    val content4 = "This is the last content."

    val horizontalFrame = HorizontalFrame(List(
      RegularConsoleElement(content1, isInteractable = true),
      RegularConsoleElement(content2, isInteractable = true),
      RegularConsoleElement(content3, isInteractable = true)))(5, isInteractable = true)

    val verticalFrame = VerticalFrame(List(
      RegularConsoleElement(content0, isInteractable = true),
      horizontalFrame,
      RegularConsoleElement(content4)))(2, isInteractable = true)

    ComposedConsoleView(verticalFrame)
  }

  override def display(): Unit = {
    val terminal: Terminal = TerminalBuilder.builder().system(true).jansi(true).build()
    terminal.enterRawMode()
    val reader: NonBlockingReader = terminal.reader()

    var running = true
    while running do {
      println("\u001b[2J") // Clear console
      view.display()

      val key = reader.read()

      if key == 27 then {
        if reader.read() == 91 then {
          val arrow = reader.read()
          arrow match {
            case 65 => view = view.navigateUp
            case 68 => view = view.navigateLeft
            case 66 => view = view.navigateDown
            case 67 => view = view.navigateRight
          }
        }
      }
      else if key == 'q'.toInt then {
        running = false
      }
    }

    reader.close()
    terminal.close()
  }
}
