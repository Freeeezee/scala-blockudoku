package blockudoku.views

import blockudoku.models.Grid
import blockudoku.services.console.GridFormatter

case class ConsoleGridView(grid: Grid) extends View {
  override def content(): String = {
    GridFormatter(grid).formatted
  }
}
