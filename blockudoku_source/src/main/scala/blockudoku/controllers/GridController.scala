package blockudoku.controllers

import blockudoku.models.{Element, Grid, Point, Tile, TileState}
import blockudoku.windows.{FocusManager, FocusState}

class GridController(val xLength: Int, val yLength: Int, elementController: ElementController, focusManager: FocusManager) {
  val grid: Grid = generateGrid(xLength, yLength)
  
  private def generateGrid(xLength: Int, yLength: Int): Grid = {
    val array = new Array[Tile](xLength * yLength)

    for y <- 0 until yLength do
      for x <- 0 until xLength do
        val index = y * xLength + x
        array(index) = Tile(index, Point(x, y))

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
        val y = baseTile.position.yPos - point.yPos
        val tile = grid.tile(x, y)
        tile.state = TileState.blocked
      }
      
      elementController.regenerate(element.slot)
      
      focusManager.focusState = FocusState.Elements
    }
  }
}
