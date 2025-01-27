package blockudoku.views.gui

import blockudoku.{Observable, Observer}
import blockudoku.commands.{CommandFactory, CommandInvoker}
import blockudoku.controllers.{ElementCollector, GridCollector}
import blockudoku.models.{Grid, Tile, TileState}
import blockudoku.services.GridPreviewBuilder
import blockudoku.windows.{FocusManager, FocusState}
import scalafx.application.Platform
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.Node
import scalafx.scene.control.Button
import scalafx.scene.image.ImageView
import scalafx.scene.layout.{HBox, StackPane, VBox}

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

    val stateImage = computeColor(gridCollector.getGrid.tile(column, row).get)

    new Button {

      alignment = Pos.Center
      padding = Insets(0)

      graphic = stateImage


      minHeight = 40
      minWidth = 40

      onAction = _ => {
        val tile = gridCollector.getGrid.tile(column, row).get
        val command = commandFactory.createSetElementCommand(elementCollector.getSelectedElement.get, tile.index)
        commandInvoker.execute(command)
      }

      gridCollector.addObserver(() => {
        Platform.runLater(() => {
          graphic = computeColor(gridCollector.getGrid.tile(column, row).get)
        })
      })

      focusManager.addObserver(() => {
        disable = focusManager.getFocusState != FocusState.Grid
      })

      ColorScheme.addObserver(() => {
        graphic = computeColor(gridCollector.getGrid.tile(column, row).get)
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
        Platform.runLater(() => {
          previewGrid match
            case Some(grid) => graphic = computeColor(grid.tile(column, row).get)
            case None => graphic = computeColor(gridCollector.getGrid.tile(column, row).get)
        })
      })
    }
  }

  private def computeColor(tile: Tile): StackPane = {

    val stackPane = new StackPane {
      alignment = Pos.Center
      children = List()
    }

    tile.state match {
      case TileState.previewInvalid =>
        val previewInvalidImage = GuiColorTranslator.createImageView("/block_red.png", 40)
        val baseImage = GuiColorTranslator.convertColor(tile.colors, 40)
        stackPane.children.add(baseImage)
        stackPane.children.add(previewInvalidImage)
      case TileState.previewValid =>
        val previewValidImage = GuiColorTranslator.createImageView("/block_green.png", 40)
        val emptyImage = GuiColorTranslator.createImageView("/background_block_final.png", 40)
        stackPane.children.add(emptyImage)
        stackPane.children.add(previewValidImage)
      case TileState.empty =>
        val emptyImage = GuiColorTranslator.createImageView("/background_block_final.png", 40)
        stackPane.children.add(emptyImage)
      case TileState.blocked =>
        val baseImage = GuiColorTranslator.convertColor(tile.colors, 40)
        stackPane.children.add(baseImage)
    }

    stackPane
  }
}
