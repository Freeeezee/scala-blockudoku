package blockudoku.views

import blockudoku.models.Grid

case class ConsoleHeadlineView(width: Int) extends View {
  private val headline = "Blockudoku_"
  
  if (width < headline.length + 2) throw new IllegalArgumentException("Width must be greater than or equal to headline length + 2")

  override def content(): String = {
    s"$line $headline $line\n\n"
  }

  private def line: String = {
    "-" * lineLength
  }

  private def lineLength: Int = {
    if (headline.length > width) 0
    else (width - headline.length - 2) / 2
  }
}
