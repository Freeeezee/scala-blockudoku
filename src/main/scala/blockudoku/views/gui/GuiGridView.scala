package blockudoku.views.gui
import blockudoku.commands.{CommandFactory, CommandInvoker}
import blockudoku.controllers.mediatorImpl.{ElementController, GridController}
import blockudoku.controllers.{ElementCollector, GridCollector}
import blockudoku.models.{Grid, Tile, TileState}
import blockudoku.observer.{Observable, Observer}
import blockudoku.services.GridPreviewBuilder
import blockudoku.windows.{FocusManager, FocusState}
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.Node
import scalafx.scene.control.Button
import scalafx.scene.image.ImageView
import scalafx.scene.layout.{HBox, VBox}
import scalafx.scene.paint.Color
import scalafx.scene.shape.Rectangle

class GuiGridView(commandFactory: CommandFactory, commandInvoker: CommandInvoker, gridCollector: GridCollector, elementCollector: ElementCollector, focusManager: FocusManager, previewBuilder: GridPreviewBuilder) extends GuiView {
  private val previewObservable = new Observable {}
  private var previewGrid: Option[Grid] = None

  override def element: Node = {
    new VBox {
      alignment = Pos.Center
      children = List()
      for row <- 0 until gridCollector.getGrid.yLength do {
        children.add(gridRow(row))
      }

    }
  }
  private def gridRow(row: Int): Node = {
    new HBox {
      alignment = Pos.Center
      children = List()
      for column <- 0 until gridCollector.getGrid.xLength do {
        children.add(gridButton(column, row))
      }
    }
  }
  private def gridButton(column: Int, row: Int): Node = {

    var stateImage = computeColor(gridCollector.getGrid.tile(column, row).get)

    new Button {
      alignment = Pos.Center
      var stateRectangle = new Rectangle {
        alignment = Pos.Center
        width = 40
        height = 40
        // graphic = stateImage
        //stroke = "black"
        //graphic = stateRectangleView
      }

      padding = Insets(0)

      graphic = stateImage

      minHeight = 30
      minWidth = 30

      onAction = _ => {
        val tile = gridCollector.getGrid.tile(column, row).get
        val command = commandFactory.createSetElementCommand(elementCollector.getSelectedElement.get, tile.index)
        commandInvoker.execute(command)
      }

      gridCollector.addObserver(() => {
        computeColor(gridCollector.getGrid.tile(column, row).get)
      })

      focusManager.addObserver(() => {
        disable = focusManager.getFocusState != FocusState.Grid
      })

      onMouseEntered = _ => {
        val previewIndex = gridCollector.getGrid.tile(column, row).get.index

        previewGrid = Some(previewBuilder.buildGrid(previewIndex))

        previewObservable.notifyObservers()
      }
      onMouseExited = _ => {
        previewGrid = None
        previewObservable.notifyObservers()
      }
        
      previewObservable.addObserver(() => {
        previewGrid match
          case Some(grid) => graphic = computeColor(grid.tile(column, row).get)
          case None => graphic = computeColor(gridCollector.getGrid.tile(column, row).get)
      })
    }
  }

  private def computeColor(tile: Tile): ImageView = {
    tile.state match {
      case TileState.empty => GuiColorTranslator.createImageView("file:src/main/resources/block_transparent.png") // Transparent
      case TileState.blocked => GuiColorTranslator.convertColor(tile.colors)
      case TileState.previewInvalid => GuiColorTranslator.createImageView("file:src/main/resources/block_red.png") // Red
      case TileState.previewValid => GuiColorTranslator.createImageView("file:src/main/resources/block_green.png") // Green
    }
  }
}
