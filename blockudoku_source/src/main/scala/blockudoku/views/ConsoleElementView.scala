package blockudoku.views

import blockudoku.models.Element
import blockudoku.services.console.ElementFormatter

case class ConsoleElementView(maxLength: Int, elements: Array[Element], width: Int) extends View {
  private val headline = "Elements_"

  override def display(): Unit = {
    displayHeadline()
    displayElements()
  }

  private def displayHeadline(): Unit = {
    println(s"\n$line $headline $line\n")
  }
  private def line: String = {
    "-" * lineLength
  }
  private def lineLength: Int = {
    if (headline.length > width) 0
    else (width - headline.length - 2) / 2
  }
  private def displayElements(): Unit = {
    val formatters = elements.map(element => ElementFormatter(element))

    for i <- 0 until maxLength do
      println(elementLine(formatters, i))
  }
  private def elementLine(formatters: Array[ElementFormatter], index: Int): String = {
    val builder = new StringBuilder

    for i <- formatters.indices do
      val part = formatters(i).formattedLine(index)
      builder.append(part)
      builder.append(" " * (15 + (maxLength - part.length)))

    builder.result()
  }
}
