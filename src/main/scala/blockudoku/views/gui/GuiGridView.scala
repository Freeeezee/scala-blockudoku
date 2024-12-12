package blockudoku.views.gui
import blockudoku.commands.{CommandFactory, CommandInvoker}
import blockudoku.controllers.{ElementController, GridController}
import blockudoku.models.{Grid, Tile, TileState}
import blockudoku.observer.{Observable, Observer}
import blockudoku.services.GridPreviewBuilder
import blockudoku.windows.{FocusManager, FocusState}
import scalafx.geometry.Pos
import scalafx.scene.Node
import scalafx.scene.control.Button
import scalafx.scene.layout.{HBox, VBox}
import scalafx.scene.paint.Color
import scalafx.scene.shape.Rectangle

class GuiGridView(commandFactory: CommandFactory, commandInvoker: CommandInvoker, gridController: GridController, elementController: ElementController, focusManager: FocusManager) extends GuiView {
  private val previewBuilder = GridPreviewBuilder(gridController, elementController)
  private val previewObservable = new Observable {}
  private var previewGrid: Option[Grid] = None

  override def element: Node = {
    new VBox {
      alignment = Pos.Center
      children = List()
      for row <- 0 until gridController.grid.value.yLength do {
        children.add(gridRow(row))
      }
    }
  }
  private def gridRow(row: Int): Node = {
    new HBox {
      alignment = Pos.Center
      children = List()
      for column <- 0 until gridController.grid.value.xLength do {
        children.add(gridButton(column, row))
      }
    }
  }
  private def gridButton(column: Int, row: Int): Node = {
    new Button {
      alignment = Pos.Center
      val stateRectangle = new Rectangle {
        alignment = Pos.Center
        width = 40
        height = 40
        fill = computeColor(gridController.grid.value.tile(column, row).get)
        //stroke = "black"
      }

      graphic = stateRectangle

      minHeight = 30
      minWidth = 30

      onAction = _ => {
        val tile = gridController.grid.value.tile(column, row).get
        val command = commandFactory.createSetElementCommand(elementController.selectedElement.value.get, tile.index)
        commandInvoker.execute(command)
      }

      gridController.addObserver(() => {
        stateRectangle.fill = computeColor(gridController.grid.value.tile(column, row).get)
      })

      focusManager.addObserver(() => {
        disable = focusManager.getFocusState != FocusState.Grid
      })

      onMouseEntered = _ => {
        val previewIndex = gridController.grid.value.tile(column, row).get.index

        previewGrid = Some(previewBuilder.buildGrid(previewIndex))

        previewObservable.notifyObservers()
      }
      onMouseExited = _ => {
        previewGrid = None
        previewObservable.notifyObservers()
      }

      previewObservable.addObserver(() => {
        previewGrid match
          case Some(grid) => stateRectangle.fill = computeColor(grid.tile(column, row).get)
          case None => stateRectangle.fill = computeColor(gridController.grid.value.tile(column, row).get)
      })
    }
  }

  private def computeColor(tile: Tile): Color = {
    tile.state match {
      case TileState.empty => Color.Transparent
      case TileState.blocked => GuiColorTranslator.convertColor(tile.colors)
      case TileState.previewInvalid => Color.Red
      case TileState.previewValid => Color.Green
    }
  }
}
