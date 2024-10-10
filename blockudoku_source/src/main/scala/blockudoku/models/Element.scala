package blockudoku.models

case class Element(structure: List[Point]) {
  def dimensions: (Int, Int, Int, Int) = {
    val xMin = structure.map(_.xPos).min
    val xMax = structure.map(_.xPos).max
    val yMin = structure.map(_.yPos).min
    val yMax = structure.map(_.yPos).max

    (xMin, xMax, yMin, yMax)
  }
}
