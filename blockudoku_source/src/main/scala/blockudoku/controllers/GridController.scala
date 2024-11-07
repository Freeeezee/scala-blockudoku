package blockudoku.controllers

import blockudoku.models.{Element, Grid, Point, Tile, TileState}

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

  private def isOccupied(element: Element, selectedPos: Int): Boolean = {
    val baseTile = grid.tiles(selectedPos)
    element.structure.exists { point => 
      val x = baseTile.position.xPos + point.xPos
      val y = baseTile.position.yPos - point.yPos
      grid.tile(x, y).state == TileState.blocked
    }
  }

  def setElement(element: Element, selectedPos: Int): Unit = {
    if (!isOccupied(element, selectedPos)) {
      val baseTile = grid.tiles(selectedPos)
      element.structure.foreach { point =>
        val x = baseTile.position.xPos + point.xPos
        val y = baseTile.position.yPos + point.yPos
        val index = y * grid.xLength + x
        val tile = grid.tiles(index)
        tile.state = TileState.blocked
      }
    }
  }
}
