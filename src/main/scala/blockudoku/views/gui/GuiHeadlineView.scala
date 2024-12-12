package blockudoku.views.gui
import scalafx.scene.Node
import scalafx.scene.text.Text

class GuiHeadlineView extends GuiView {


  override def element: Node = {
    new Text {
      text = "Blockudoku"
      style = "-fx-font-size: 48pt"
    }
  }
}
