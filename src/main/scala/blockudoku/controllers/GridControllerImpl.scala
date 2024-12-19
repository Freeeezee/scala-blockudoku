package blockudoku.controllers

import blockudoku.models.*
import blockudoku.observer.Observable
import blockudoku.windows.{FocusManager, FocusState}
import scalafx.beans.property.ObjectProperty

class GridControllerImpl(focusManager: FocusManager) extends GridController {
  var grid: Grid = generateGrid(9, 9)
  
  private def generateGrid(xLength: Int, yLength: Int): Grid = {
    var list = List[Tile]()
    

    for y <- 0 until yLength do
      for x <- 0 until xLength do
        val index = y * xLength + x
        list = list :+ Tile(index, Point(x, y))

    Grid(xLength, yLength)(list)
  }

  def setElement(element: Element, selectedPos: Int): Boolean = {
    val tiles = grid.elementTiles(element, selectedPos)
    
    
    if tiles.isEmpty || isOccupied(tiles.get) then {
      return false
    }
    
    grid = grid.copyWithNewState(tiles.get, TileState.blocked, element.colors)
    
    notifyObservers()
    true
  }

  private def isOccupied(tiles: List[Tile]): Boolean = {
    tiles.exists(tile => tile.state == TileState.blocked)
  }
}
