package blockudoku.views.console

import blockudoku.models.Grid
import blockudoku.views.console.ConsoleView
import blockudoku.views.console.composed.{ConsoleElement, RegularConsoleElement}

case class ConsoleHeadlineView(width: Int) extends ConsoleView {
  private val headline = "Blockudoku_"

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
