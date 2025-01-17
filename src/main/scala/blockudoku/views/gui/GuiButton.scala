package blockudoku.views.gui

import javafx.event.{ActionEvent, EventHandler}
import scalafx.scene.control.Button
import scalafx.scene.effect.{DropShadow, Glow}
import scalafx.scene.input.MouseEvent
import scalafx.scene.paint.Color
import scalafx.scene.text.Font
import scalafx.Includes._

class GuiButton(buttonText: String, action: EventHandler[ActionEvent]) extends Button {

  private val buttonFont: Font = Font.loadFont(getClass.getResourceAsStream("/Audiowide-Regular.ttf"), 20)

  protected val defaultStyle = "-fx-font-family: 'Audiowide'; -fx-font-size: 16; -fx-background-color: #8499B1; -fx-text-fill: white;"
  protected val hoverStyle = "-fx-font-family: 'Audiowide'; -fx-font-size: 16; -fx-background-color: #666; -fx-text-fill: white;"
  protected val pressedStyle = "-fx-font-family: 'Audiowide'; -fx-font-size: 16; -fx-background-color: #222; -fx-text-fill: white;"

  font = buttonFont
  style = defaultStyle
  text = buttonText
  onAction = action

  hover.onChange { (_, _, isHovered) =>
    if (isHovered) {
      effect = new Glow(0.4)
      style = hoverStyle
    } else {
      effect = null
      style = defaultStyle
    }
  }

  onMousePressed = (_: MouseEvent) => {
    effect = new DropShadow(10, Color.Black)
    style = pressedStyle
  }

  onMouseReleased = (_: MouseEvent) => {
    if (hover.value) {
      effect = new Glow(0.4)
      style = hoverStyle
    } else {
      effect = null
      style = defaultStyle
    }
  }
}
