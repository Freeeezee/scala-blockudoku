package blockudoku.models

case class Tile(index: Int, position: Point, colors: Int = 0, state: TileState = TileState.empty):
  var tileIndex: Int = index
  
  def copy(): Tile = Tile(index, position, colors, state)

enum TileState {
  case empty
  case blocked
  case previewValid
  case previewInvalid
}
