package blockudoku.views.gui

import blockudoku.commands.CommandInvoker
import scalafx.geometry.Pos
import scalafx.scene.Node
import scalafx.scene.control.Button
import scalafx.scene.layout.HBox
import scalafx.scene.text.Font

class GuiUndoRedoView(commandInvoker: CommandInvoker) extends GuiView {

  override def element: Node = {
    new HBox {
      alignment = Pos.Center
      spacing = 10
      children = List(
        new Button {
          text = "Undo"
          font = Font.loadFont(getClass.getResourceAsStream("/Audiowide-Regular.ttf"), 20)
          onAction = _ => {
            commandInvoker.undo()
          }
        },
        new Button {
          text = "Redo"
          font = Font.loadFont(getClass.getResourceAsStream("/Audiowide-Regular.ttf"), 20)
          onAction = _ => {
            commandInvoker.redo()
          }
        }
      )
    }
  }
}
