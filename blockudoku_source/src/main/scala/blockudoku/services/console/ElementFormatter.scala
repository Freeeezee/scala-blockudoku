package blockudoku.services.console

import blockudoku.models.{Element, Point}

case class ElementFormatter(element: Element) {
  private val (xMin: Int, xMax: Int, yMin: Int, yMax: Int) = element.dimensions

  private val lines: Array[String] = initialize
  private def initialize: Array[String] = {
    val xSize = 1 + math.abs(xMin) + math.abs(xMax)
    val ySize = 1 + math.abs(yMin) + math.abs(yMax)

    val lines = new Array[String](ySize)

    for y <- ySize - 1 to 0 by -1 do
      val builder = new StringBuilder
      for x <- 0 until xSize do
        builder.append(content(x + xMin, y + yMin))
      lines(y) = builder.result()

    lines
  }

  def formattedLine(index: Int): String = {
    if index < lines.length then lines(index)
    else " " * (xMax - xMin + 1)
  }

  def content(xPos: Int, yPos: Int): String = {
    if element.structure.contains(Point(xPos, yPos)) then "x"
    else " "
  }
}
