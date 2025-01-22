package blockudoku.models

/**
 * Represents a tile in a grid.
 * @param index The index of the tile.
 * @param position The position of the tile.
 * @param colors The colors of the tile.
 * @param state The state of the tile.
 */
case class Tile(index: Int, position: Point, colors: Int = 0, state: TileState = TileState.empty):
  var tileIndex: Int = index
  
  def copy(): Tile = Tile(index, position, colors, state)

/**
 * Represents the state of a tile.
 */
enum TileState {
  case empty
  case blocked
  case previewValid
  case previewInvalid
}
