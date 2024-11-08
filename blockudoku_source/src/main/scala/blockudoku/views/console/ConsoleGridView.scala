package blockudoku.views.console

import blockudoku.controllers.{ElementController, GridController}
import blockudoku.models.{Grid, Tile, TileState}
import blockudoku.services.GridPreviewBuilder
import blockudoku.services.console.ConsoleStyle
import blockudoku.views.console.composed.{ConsoleElement, HorizontalFrame, RegularConsoleElement, VerticalFrame}
import blockudoku.windows.{FocusManager, FocusState}

case class ConsoleGridView(gridController: GridController, elementController: ElementController,
                           focusManager: FocusManager) extends ConsoleView(focusManager) {
  override val interactableFocusStates: Set[FocusState] = Set(FocusState.Grid)

  private val previewBuilder = GridPreviewBuilder(gridController, elementController)

  private var highlightedIndex = -1
  
  override def consoleElement: ConsoleElement = formatted

  private def formatted: ConsoleElement = {
    var list = List[ConsoleElement]()

    val grid = previewBuilder.buildGrid(highlightedIndex)

    for row <- 0 until grid.yLength do {
      list = list :+ divider(grid)
      list = list :+ formattedHorizontal(row, grid)
    }

    list = list :+ divider(grid)

    VerticalFrame(list)(0, isInteractable = focused)
  }

  private def divider(grid: Grid): ConsoleElement = {
    val builder = new StringBuilder

    builder.append('x')
    builder.append("----x" * grid.xLength)

    RegularConsoleElement(builder.result())
  }

  private def formattedHorizontal(row: Int, grid: Grid): ConsoleElement = {
    var list = List[ConsoleElement]()

    for column <- 0 until grid.xLength do {
      list = list ++ tileElements(grid.tile(column, row).get)
    }

    list = list :+ RegularConsoleElement("|")

    HorizontalFrame(list)(0, isInteractable = true)
  }

  private def tileElements(tile: Tile): List[ConsoleElement] = {
    List[ConsoleElement](
      RegularConsoleElement("| "),
      RegularConsoleElement(tileContent(tile), isInteractable = true, 
        onHighlighted = () => onTileHighlighted(tile),
        onSelect = () => onTileSelected(tile)),
      RegularConsoleElement(" ")
    )
  }

  private def tileContent(tile: Tile): String = {
    tile.state match
      case TileState.empty => "  "
      case TileState.blocked => "xx"
      case TileState.previewValid => ConsoleStyle.colorized("xx", ConsoleStyle.GREEN)
      case TileState.previewInvalid => ConsoleStyle.colorized("xx", ConsoleStyle.RED)
  }
  
  private def onTileHighlighted(tile: Tile): Unit = {
    highlightedIndex = tile.index
  }
  
  private def onTileSelected(tile: Tile): Unit = {
    gridController.setElement(elementController.selectedElement.get, tile.index)
    highlightedIndex = -1
  }
}
