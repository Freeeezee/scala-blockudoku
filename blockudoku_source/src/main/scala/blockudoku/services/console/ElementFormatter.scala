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
        if element.structure.contains(Point(x + xMin, y + yMin)) then builder.append("x")
        else builder.append(" ")
      lines(y) = builder.result()

    lines
  }

  def formattedLine(index: Int): String = {
    if index < lines.length then lines(index)
    else " " * (xMax - xMin + 1)
  }
}
