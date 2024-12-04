package blockudoku.models

case class Tile(index: Int, position: Point, state: TileState = TileState.empty):
  var tileIndex: Int = index
  
  def copy(): Tile = Tile(index, position, state = state)

enum TileState {
  case empty
  case blocked
  case previewValid
  case previewInvalid
}
