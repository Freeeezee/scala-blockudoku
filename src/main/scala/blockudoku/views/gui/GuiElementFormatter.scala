package blockudoku.views.gui

import blockudoku.models.{Element, Point}
import scalafx.scene.Node
import scalafx.scene.layout.{HBox, VBox}
import scalafx.scene.paint.Color
import scalafx.scene.shape.Rectangle
import scalafx.scene.image.ImageView
import scalafx.scene.layout.StackPane

class GuiElementFormatter (element: Element) {
  private val (xMin: Int, xMax: Int, yMin: Int, yMax: Int) = element.dimensions

  def content: Node = {
    new VBox {
      children = List()

      for y <- yMax to yMin by -1 do
        children.add(row(y))
    }
  }
  private def row(y: Int): Node = {
    new HBox {
      children = List()
      for x <- xMin to xMax do
        val point = Point(x, y)
        val rect = new StackPane {
          children = if (element.structure.contains(point)) {
            Seq(
              new Rectangle {
                width = 30
                height = 30
                fill = Color.Transparent
              },
              GuiColorTranslator.convertColor(element.colors) // Gibt ImageView zurück
            )
          } else {
            Seq(
              new Rectangle {
                width = 30
                height = 30
                fill = Color.Transparent
              }
            )
          }
        }
        children.add(rect)
    }
  }
}