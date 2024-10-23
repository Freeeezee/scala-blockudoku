package blockudoku.models

class Grid(val xLength: Int, val yLength: Int)(tiles: Array[Tile]):
  def tile(xPos: Int, yPos: Int): Tile = tiles(xPos * xLength + yPos)