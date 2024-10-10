package blockudoku.controllers

import blockudoku.models.{Grid, Point, Tile}

case class GridController() {
  val grid: Grid = generateGrid(10, 10)
  
  private def generateGrid(xLength: Int, yLength: Int): Grid = {
    val array = new Array[Tile](xLength * yLength)

    for i <- 0 until xLength do
      for j <- 0 until yLength do
        val index = i * xLength + j
        array(index) = Tile(index, Point(i, j))

    Grid(xLength, yLength)(array)
  }
}
