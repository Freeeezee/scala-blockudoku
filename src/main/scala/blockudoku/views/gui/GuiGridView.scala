package blockudoku.views.gui
import blockudoku.commands.{CommandFactory, CommandInvoker}
import blockudoku.controllers.{ElementController, GridController}
import blockudoku.models.{Tile, TileState}
import blockudoku.observer.Observer
import scalafx.geometry.Pos
import scalafx.scene.Node
import scalafx.scene.control.Button
import scalafx.scene.layout.{HBox, VBox}
import scalafx.scene.paint.Color
import scalafx.scene.shape.Rectangle

class GuiGridView(commandFactory: CommandFactory, commandInvoker: CommandInvoker, gridController: GridController, elementController: ElementController) extends GuiView {

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
        width = 30
        height = 30
        fill = computeColor(gridController.grid.value.tile(column, row).get)
        //stroke = "black"
      }

      graphic = stateRectangle

      minHeight = 40
      minWidth = 40

      gridController.addObserver(new Observer {
        override def update(): Unit = {
          stateRectangle.fill = computeColor(gridController.grid.value.tile(column, row).get)
          //text = buttonContent(column, row)
        }
      })
      onAction = _ => {
        val tile = gridController.grid.value.tile(column, row).get
        val command = commandFactory.createSetElementCommand(elementController.selectedElement.value.get, tile.index)
        commandInvoker.execute(command)
      }
    }
  }

  private def computeColor(tile: Tile): Color = {
    tile.state match {
      case TileState.empty => Color.Transparent
      case TileState.blocked => Color.Black
      case TileState.previewInvalid => Color.Red
      case TileState.previewValid => Color.Green
    }
  }
}
