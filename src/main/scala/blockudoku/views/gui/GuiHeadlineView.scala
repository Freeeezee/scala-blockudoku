package blockudoku.views.gui
import scalafx.scene.Node
import scalafx.scene.text.{Font, FontWeight, Text}

class GuiHeadlineView extends GuiView {

  override def element: Node = {
    new Text {
      text = "Blockudoku"
       font = Font.loadFont(getClass.getResourceAsStream("/Audiowide-Regular.ttf"), 50)
    }
  }
}
