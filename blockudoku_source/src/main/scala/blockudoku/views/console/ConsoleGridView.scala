package blockudoku.views.console

import blockudoku.controllers.GridController
import blockudoku.models.{Tile, TileState}
import blockudoku.views.console.ConsoleView
import blockudoku.views.console.composed.{ConsoleElement, HorizontalFrame, RegularConsoleElement, VerticalFrame}

case class ConsoleGridView(gridController: GridController) extends ConsoleView {
  override def consoleElement: ConsoleElement = formatted

  private def formatted: ConsoleElement = {
    var list = List[ConsoleElement]()

    for row <- 0 until gridController.grid.yLength do {
      list = list :+ divider
      list = list :+ formattedHorizontal(row)
    }

    list = list :+ divider

    VerticalFrame(list)(0, isInteractable = true)
  }

  private def divider: ConsoleElement = {
    val builder = new StringBuilder

    builder.append('x')
    builder.append("----x" * gridController.grid.xLength)

    RegularConsoleElement(builder.result())
  }

  private def formattedHorizontal(row: Int): ConsoleElement = {
    var list = List[ConsoleElement]()

    for column <- 0 until gridController.grid.xLength do {
      list = list ++ tileElements(gridController.grid.tile(row, column))
    }

    list = list :+ RegularConsoleElement("|")

    HorizontalFrame(list)(0, isInteractable = true)
  }

  private def tileElements(tile: Tile): List[ConsoleElement] = {
    List[ConsoleElement](
      RegularConsoleElement("| "),
      RegularConsoleElement(tileContent(tile), isInteractable = true),
      RegularConsoleElement(" ")
    )
  }

  private def tileContent(tile: Tile): String = {
    tile.state match
      case TileState.empty => f"${tile.index}%02d"
      //case TileState.blocked => "xx"
  }
}
