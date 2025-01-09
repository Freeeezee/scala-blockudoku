package blockudoku.views.console

import blockudoku.commands.{CommandFactory, CommandInvoker}
import blockudoku.controllers.mediatorImpl.{ElementController, GridController}
import blockudoku.controllers.{ControllerMediator, ElementCollector, GridCollector}
import blockudoku.models.{Grid, Tile, TileState}
import blockudoku.observer.Observer
import blockudoku.services.GridPreviewBuilder
import blockudoku.services.console.ConsoleStyle
import blockudoku.views.console.composed.{ConsoleElement, HorizontalFrame, RegularConsoleElement, VerticalFrame}
import blockudoku.windows.{FocusManager, FocusState, Window}
import com.google.inject.Inject

case class ConsoleGridView @Inject (commandFactory: CommandFactory, commandInvoker: CommandInvoker, gridCollector: GridCollector, elementCollector: ElementCollector,
                           focusManager: FocusManager, window: Window, previewBuilder: GridPreviewBuilder) extends ConsoleView(focusManager, window), Observer {
  override val interactableFocusStates: Set[FocusState] = Set(FocusState.Grid)

  private var highlightedIndex = -1
  
  override def consoleElement: ConsoleElement = {
    formatted
  }

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
  
  def onTileHighlighted(tile: Tile): Unit = {
    highlightedIndex = tile.index
  }
  
  private def onTileSelected(tile: Tile): Unit = {
    val command = commandFactory.createSetElementCommand(elementCollector.getSelectedElement.get, tile.index)
    commandInvoker.execute(command)
    highlightedIndex = -1
  }
  override def update(): Unit = {
    setUpdated()
  }
}
