package blockudoku.controllers

import blockudoku.models.*
import blockudoku.observer.Observable
import blockudoku.windows.{FocusManager, FocusState}

class GridControllerImpl(val xLength: Int, val yLength: Int, elementController: ElementController, focusManager: FocusManager) extends GridController {
  val grid: Grid = generateGrid(xLength, yLength)
  
  private def generateGrid(xLength: Int, yLength: Int): Grid = {
    val array = new Array[Tile](xLength * yLength)

    for y <- 0 until yLength do
      for x <- 0 until xLength do
        val index = y * xLength + x
        array(index) = Tile(index, Point(x, y))

    Grid(xLength, yLength)(array)
  }

  def setElement(element: Element, selectedPos: Int): Unit = {
    val tiles = grid.elementTiles(element, selectedPos)
    
    
    if tiles.isEmpty || isOccupied(tiles.get) then {
      return
    }
    
    tiles.get.foreach(tile => tile.state = TileState.blocked)
    notifyObservers()
  }

  private def isOccupied(tiles: List[Tile]): Boolean = {
    tiles.exists(tile => tile.state == TileState.blocked)
  }
}
