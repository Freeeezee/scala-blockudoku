package blockudoku.controllers

import blockudoku.models.*
import blockudoku.observer.Observable
import blockudoku.windows.{FocusManager, FocusState}

class GridControllerImpl(val xLength: Int, val yLength: Int, elementController: ElementController, focusManager: FocusManager) extends GridController {
  var grid: Grid = generateGrid(xLength, yLength)
  
  private def generateGrid(xLength: Int, yLength: Int): Grid = {
    var list = List[Tile]()
    

    for y <- 0 until yLength do
      for x <- 0 until xLength do
        val index = y * xLength + x
        list = list :+ Tile(index, Point(x, y))

    Grid(xLength, yLength)(list)
  }

  def setElement(element: Element, selectedPos: Int): Unit = {
    val tiles = grid.elementTiles(element, selectedPos)
    
    
    if tiles.isEmpty || isOccupied(tiles.get) then {
      return
    }
    
    grid = grid.copyWithNewState(tiles.get, TileState.blocked)
    
    notifyObservers()
  }

  private def isOccupied(tiles: List[Tile]): Boolean = {
    tiles.exists(tile => tile.state == TileState.blocked)
  }
}
