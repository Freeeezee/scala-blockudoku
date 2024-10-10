package blockudoku.services.console

import blockudoku.models.{Grid, Tile}

case class GridFormatter(grid: Grid) {
  def formatted: String = {
    val builder = new StringBuilder

    for i <- 0 until grid.xLength do
      builder.append(horizontalLine)
      for j <- 0 until grid.yLength do
        builder.append(formattedTile(grid.tile(i, j)))
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
      case empty => f"${tile.index}%02d"
      case blocked => "xx"
  }
}
