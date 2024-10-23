package blockudoku.controllers

import blockudoku.models.{Grid, Point, Tile}

class GridController(val xLength: Int, val yLength: Int) {
  val grid: Grid = generateGrid(xLength, yLength)
  
  private def generateGrid(xLength: Int, yLength: Int): Grid = {
    val array = new Array[Tile](xLength * yLength)

    for i <- 0 until xLength do
      for j <- 0 until yLength do
        val index = i * xLength + j
        array(index) = Tile(index, Point(i, j))

    Grid(xLength, yLength)(array)
  }
}
