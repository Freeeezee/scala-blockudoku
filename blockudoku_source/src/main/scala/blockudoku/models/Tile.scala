package blockudoku.models

case class Tile(index: Int, position: Point):
  var state : TileState = TileState.empty
  var tileIndex: Int = index

enum TileState {
  case empty
  case blocked
}
