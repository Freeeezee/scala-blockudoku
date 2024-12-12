package blockudoku.views.console

import blockudoku.views.console.composed.{ConsoleElement, RegularConsoleElement}
import blockudoku.windows.{FocusManager, FocusState, Window}

case class ConsoleHeadlineView(width: Int, focusManager: FocusManager, window: Window) extends ConsoleView(focusManager, window) {
  override val interactableFocusStates: Set[FocusState] = Set()
  
  private val headline = "Blockudoku_"

  if (width < headline.length + 2) throw new IllegalArgumentException("Width must be greater than or equal to headline length + 2")

  override def consoleElement: ConsoleElement = {
    RegularConsoleElement(content)
  }
  
  private def content: String = {
    s"$line $headline $line\n"
  }

  private def line: String = {
    "-" * lineLength
  }

  private def lineLength: Int = {
    if (headline.length > width) 0
    else (width - headline.length - 2) / 2
  }
}
