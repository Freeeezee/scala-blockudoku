package blockudoku.models

import blockudoku.ObservableObject

class Grid(val xLength: Int, val yLength: Int)(val tiles: Array[Tile]) extends ObservableObject[Grid]:
  def tile(xPos: Int, yPos: Int): Tile = tiles(xPos * xLength + yPos)