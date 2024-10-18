package blockudoku.services.console

import blockudoku.models.{Grid, Tile, TileState}

case class GridFormatter(grid: Grid) {
  def formatted: String = {
    val builder = new StringBuilder

    for y <- 0 until grid.yLength do
      builder.append(horizontalLine)
      for x <- 0 until grid.xLength do
        builder.append(formattedTile(grid.tile(x, y)))
      builder.append("|\n")
      
    builder.append(horizontalLine)

    builder.result()
  }

  private def horizontalLine: String = {
    val builder = new StringBuilder

    builder.append('x')
    builder.append("----x" * grid.xLength)
    builder.append("\n")

    builder.result()
  }

  private def formattedTile(tile: Tile): String = {
    s"| ${tileContent(tile)} "
  }

  private def tileContent(tile: Tile): String = {
    tile.state match
      case TileState.empty => f"${tile.index}%02d"
      case TileState.blocked => "xx"
  }
}
