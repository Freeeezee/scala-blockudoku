package blockudoku.models

/**
 * Represents a placeable element in the game.
 * @param structure List of points that make up the element.
 * @param slot The slot the element is in.
 * @param colors Index of the color of the element.
 */
case class Element(structure: List[Point], slot: Int, colors: Int) {
  def dimensions: (Int, Int, Int, Int) = {
    val xMin = structure.map(_.xPos).min
    val xMax = structure.map(_.xPos).max
    val yMin = structure.map(_.yPos).min
    val yMax = structure.map(_.yPos).max

    (xMin, xMax, yMin, yMax)
  }
}
