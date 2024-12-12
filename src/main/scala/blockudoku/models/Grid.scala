package blockudoku.models

import scala.util.boundary
import scala.util.boundary.break

case class Grid(xLength: Int, yLength: Int)(val tiles: List[Tile]):
  def tile(xPos: Int, yPos: Int): Option[Tile] = {
    if xPos < 0 || xPos >= xLength || yPos < 0 || yPos >= yLength then
      None
    else
      Some(tiles(yPos * xLength + xPos))
  }

  def elementTiles(element: Element, selectedPos: Int): Option[List[Tile]] = {
    val baseTile = tiles(selectedPos)

    var list = List(baseTile)

    boundary:
      element.structure.foreach { point =>
        val x = baseTile.position.xPos + point.xPos
        val y = baseTile.position.yPos - point.yPos
        val currentTile = tile(x, y)

        if currentTile.isEmpty then
          break(None)

        list = list :+ currentTile.get
      }

    if list.length != element.structure.length + 1 then
      None
    else
      Some(list)
  }

  def copy(): Grid = Grid(xLength, yLength)(tiles.map(_.copy()))
  
  def copyWithNewState(updatedTiles: List[Tile], newState: TileState, newColors: Colors): Grid = {
    var tileList = List[Tile]()
    
    for (i <- tiles.indices) {
      if updatedTiles.exists(tile => tile.index == i) then
        tileList = tileList :+ tiles(i).copy(state = newState, colors = newColors)
      else
        tileList = tileList :+ tiles(i).copy()
    }
    Grid(xLength, yLength)(tileList)
  }

  def copyWithNewState(updatedTiles: List[Tile], newState: TileState): Grid = {
    var tileList = List[Tile]()

    for (i <- tiles.indices) {
      if updatedTiles.exists(tile => tile.index == i) then
        tileList = tileList :+ tiles(i).copy(state = newState)
      else
        tileList = tileList :+ tiles(i).copy()
    }
    Grid(xLength, yLength)(tileList)
  }