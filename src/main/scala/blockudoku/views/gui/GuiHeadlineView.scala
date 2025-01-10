package blockudoku.views.gui
import scalafx.geometry.Pos
import scalafx.scene.Node
import scalafx.scene.control.Button
import scalafx.scene.layout.VBox
import scalafx.scene.text.{Font, FontWeight, Text}
import scalafx.stage

class GuiHeadlineView extends GuiView {

  override def element: Node = {
    new VBox {
      alignment = Pos.Center
      spacing = 10
      children = List(
        new Text {
        text = "Blockudoku"
        font = Font.loadFont(getClass.getResourceAsStream("/Audiowide-Regular.ttf"), 50)
        }
      )
    }
  }
}
