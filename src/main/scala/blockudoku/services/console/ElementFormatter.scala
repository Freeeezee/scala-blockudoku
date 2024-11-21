package blockudoku.services.console

import blockudoku.models.{Element, Point}

case class ElementFormatter(element: Element) {
  private val (xMin: Int, xMax: Int, yMin: Int, yMax: Int) = element.dimensions

  def content: String = {
    val builder = new StringBuilder

    for y <- yMax to yMin by -1 do
      for x <- xMin to xMax do
        val point = Point(x, y)
        val char = if element.structure.contains(point) then 'X' else ' '
        builder.append(char)

      builder.append('\n')
      
    builder.result()
  }
}
