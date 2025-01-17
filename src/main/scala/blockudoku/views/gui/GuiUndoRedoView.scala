package blockudoku.views.gui

import blockudoku.commands.CommandInvoker
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.Node
import scalafx.scene.control.Button
import scalafx.scene.layout.HBox
import scalafx.scene.text.Font

class GuiUndoRedoView(commandInvoker: CommandInvoker) extends GuiView {
  
  override def element: Node = {
    new HBox {
      alignment = Pos.Center
      spacing = 10
      margin = Insets(10)
      children = List(
        new GuiButton("Undo", _ => commandInvoker.undo()),
        new GuiButton("Redo", _ => commandInvoker.redo())
      )
    }
  }
}
