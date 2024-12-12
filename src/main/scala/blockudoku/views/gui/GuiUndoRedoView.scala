package blockudoku.views.gui

import blockudoku.commands.CommandInvoker
import scalafx.geometry.Pos
import scalafx.scene.Node
import scalafx.scene.control.Button
import scalafx.scene.layout.HBox

class GuiUndoRedoView(commandInvoker: CommandInvoker) extends GuiView {

  override def element: Node = {
    new HBox {
      alignment = Pos.Center
      spacing = 10
      children = List(
        new Button {
          text = "Undo"
          onAction = _ => {
            commandInvoker.undo()
          }
        },
        new Button {
          text = "Redo"
          onAction = _ => {
            commandInvoker.redo()
          }
        }
      )
    }
  }

}
