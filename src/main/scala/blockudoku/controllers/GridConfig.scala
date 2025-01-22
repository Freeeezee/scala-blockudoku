package blockudoku.controllers

/**
 * Configuration options for the grid.
 */
case class GridConfig()  {
  /**
   * The length of the grid in the x direction.
   */
  val xLength: Int = 9
  
  /**
   * The length of the grid in the y direction.
   */
  val yLength: Int = 9
}

