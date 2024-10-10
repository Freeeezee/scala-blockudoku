package blockudoku.views

import blockudoku.models.Grid

case class ConsoleHeadlineView(width: Int) extends View {
  private val headline = "Blockudoku_"

  override def display(): Unit = {
    println(s"$line $headline $line\n")
  }

  private def line: String = {
    "-" * lineLength
  }

  private def lineLength: Int = {
    if (headline.length > width) 0
    else (width - headline.length - 2) / 2
  }
}
