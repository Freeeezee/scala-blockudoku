package blockudoku.views

import blockudoku.models.Element
import blockudoku.services.console.ElementFormatter

case class ConsoleElementView(maxLength: Int, elements: Array[Element], width: Int) extends View {
  private val headline = "Elements_"
  private val spacing = 15

  override def content(): String = {
    s"$headlineContent$elementContent$elementNumbersContent"
  }

  private def headlineContent: String = {
    s"\n$line $headline $line\n\n"
  }
  private def line: String = {
    "-" * lineLength
  }
  private def lineLength: Int = {
    if (headline.length > width) 0
    else (width - headline.length - 2) / 2
  }
  private def elementContent: String = {
    val formatters = elements.map(element => ElementFormatter(element))

    val str = new StringBuilder()
    
    for i <- 0 until maxLength do
      str.append(s"${elementLine(formatters, i)}\n")
      
    str.result()
  }
  private def elementLine(formatters: Array[ElementFormatter], index: Int): String = {
    val builder = new StringBuilder

    for i <- formatters.indices do
      val part = formatters(i).formattedLine(index)
      builder.append(part)
      builder.append(" " * (spacing + (maxLength - part.length)))

    builder.result()
  }

  private def elementNumbersContent: String = {
    val builder = new StringBuilder
    builder.append("\n")

    for i <- elements.indices do
      builder.append(i)
      builder.append(" " * (maxLength - 1 + spacing))

    builder.append("\n")
    builder.result()
  }
}
