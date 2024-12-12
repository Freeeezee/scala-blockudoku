package blockudoku.controllers

import blockudoku.models.*
import blockudoku.observer.Observable
import blockudoku.windows.{FocusManager, FocusState}
import scalafx.beans.property.ObjectProperty

class GridControllerImpl(val xLength: Int, val yLength: Int, elementController: ElementController, focusManager: FocusManager) extends GridController {
  val grid: ObjectProperty[Grid] = ObjectProperty(generateGrid(xLength, yLength))
  
  private def generateGrid(xLength: Int, yLength: Int): Grid = {
    var list = List[Tile]()
    

    for y <- 0 until yLength do
      for x <- 0 until xLength do
        val index = y * xLength + x
        list = list :+ Tile(index, Point(x, y))

    Grid(xLength, yLength)(list)
  }

  def setElement(element: Element, selectedPos: Int): Boolean = {
    val tiles = grid.value.elementTiles(element, selectedPos)
    
    
    if tiles.isEmpty || isOccupied(tiles.get) then {
      return false
    }
    
    grid.value = grid.value.copyWithNewState(tiles.get, TileState.blocked)
    
    notifyObservers()
    true
  }

  private def isOccupied(tiles: List[Tile]): Boolean = {
    tiles.exists(tile => tile.state == TileState.blocked)
  }
}
