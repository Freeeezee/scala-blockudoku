package blockudoku.views.gui
import blockudoku.commands.{CommandFactory, CommandInvoker}
import blockudoku.controllers.{ElementController, GridController}
import blockudoku.models.{Tile, TileState}
import blockudoku.observer.Observer
import scalafx.Includes.handle
import scalafx.event.ActionEvent
import scalafx.scene.Node
import scalafx.scene.control.Button
import scalafx.scene.layout.{HBox, VBox}

class GuiGridView(commandFactory: CommandFactory, commandInvoker: CommandInvoker, gridController: GridController, elementController: ElementController) extends GuiView {

  override def element: Node = {
    new VBox {
      children = List()
      for row <- 0 until gridController.grid.value.yLength do {
        children.add(gridRow(row))
      }
    }
  }
  private def gridRow(row: Int): Node = {
    new HBox {
      children = List()
      for column <- 0 until gridController.grid.value.xLength do {
        children.add(gridButton(column, row))
      }
    }
  }
  private def gridButton(column: Int, row: Int): Node = {
    new Button {
      text = buttonContent(column, row)
      minHeight = 40
      minWidth = 40
      gridController.grid.onChange { (_, _, _) =>
        text = buttonContent(column, row)
      }
      onAction = _ => {
        print("hi")
        val tile = gridController.grid.value.tile(column, row).get
        val command = commandFactory.createSetElementCommand(elementController.selectedElement.value.get, tile.index)
        commandInvoker.execute(command)
      }
    }
  }
  private def buttonContent(column: Int, row: Int): String = {
    gridController.grid.value.tile(column, row) match {
      case Some(tile) => computeButtonState(tile)
      case None => ""
    }
  }
  private def computeButtonState(tile: Tile): String = {
    tile.state match {
      case TileState.empty => ""
      case TileState.blocked => "X"
    }
  }
}
