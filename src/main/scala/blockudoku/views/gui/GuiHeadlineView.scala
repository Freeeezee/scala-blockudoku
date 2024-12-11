package blockudoku.views.gui
import scalafx.geometry.Pos
import scalafx.scene.Node
import scalafx.scene.paint.Color.DarkRed
import scalafx.scene.text.Text

class GuiHeadlineView extends GuiView {


  override def element: Node = {
    new Text {
      text = "Blockudoku"
      style = "-fx-font-size: 48pt"
    }
  }
}
