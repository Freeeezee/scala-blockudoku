package blockudoku.models

import scala.util.boundary
import scala.util.boundary.break

case class Grid(xLength: Int, yLength: Int)(val tiles: Array[Tile]):
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

    Some(list)
  }

  def copy(): Grid = Grid(xLength, yLength)(tiles.map(_.copy()))