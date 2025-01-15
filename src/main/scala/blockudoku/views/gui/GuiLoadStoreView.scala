package blockudoku.views.gui
import blockudoku.saving.SaveManager
import scalafx.geometry.Pos
import scalafx.scene.Node
import scalafx.scene.layout.HBox

class GuiLoadStoreView(saveManager: SaveManager) extends GuiView {
  
  override def element: Node = {
    new HBox {
      alignment = Pos.Center
      spacing = 10
      children = List(
        new GuiButton("Load", _ => saveManager.load()),
        new GuiButton("Save", _ => saveManager.save())
      )
    }
  }
}
