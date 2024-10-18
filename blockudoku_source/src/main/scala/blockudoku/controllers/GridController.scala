package blockudoku.controllers

import blockudoku.models.{Element, Grid, Point, Tile, TileState}
import blockudoku.windows

class GridController() {
  val grid: Grid = generateGrid(9, 9)
  //var occupiedPoints: Set[Point] = Set() // speichert alle besetzten Punkte

  private def generateGrid(xLength: Int, yLength: Int): Grid = { // x: Spalten, y: Zeilen
    val array = new Array[Tile](xLength * yLength)

    for y <- 0 until yLength do
      for x <- 0 until xLength do
        val index = y * xLength + x
        array(index) = Tile(index, Point(x, y))

    Grid(xLength, yLength)(array)
  }

  // Parameter: Element und Position (siehe generate git)
  private def isOccupied(element: Element, selectedPos: Int): Boolean = { // Unit = void
    val baseTile = grid.tiles(selectedPos)
    element.structure.exists { point => // iterates over all points in the structure
      val x = baseTile.position.xPos + point.xPos
      val y = baseTile.position.yPos - point.yPos
      println("Points" + x + " " + y)
      //val index = y * grid.xLength + x
      println("Tile is occupied" + grid.tile(x, y).index)
      grid.tile(x, y).state == TileState.blocked
      //grid.tiles(index).state == TileState.blocked
    }
  }

  def setElement(element: Element, selectedPos: Int): Unit = {
    if (!isOccupied(element, selectedPos)) {
      val baseTile = grid.tiles(selectedPos) // baseTile = anchor point
      element.structure.foreach { point =>
        val x = baseTile.position.xPos + point.xPos
        val y = baseTile.position.yPos + point.yPos
        val index = y * grid.xLength + x
        val tile = grid.tiles(index)
        tile.state = TileState.blocked
        //tile.index = index
      }
    }
  }
}
