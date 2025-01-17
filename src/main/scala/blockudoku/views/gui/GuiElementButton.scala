package blockudoku.views.gui

import javafx.event.{ActionEvent, EventHandler}
import scalafx.scene.input.MouseEvent
import scalafx.scene.effect.{DropShadow, Glow}
import scalafx.scene.paint.Color
import scalafx.Includes._

class GuiElementButton(buttonText: String, action: EventHandler[ActionEvent]) extends GuiButton(buttonText: String, action: EventHandler[ActionEvent]) {

  override val defaultStyle = "-fx-font-family: 'Audiowide'; -fx-font-size: 16; -fx-background-color: transparent; -fx-text-fill: white;"
  override val hoverStyle = "-fx-font-family: 'Audiowide'; -fx-font-size: 16; -fx-background-color: transparent; -fx-text-fill: white;"
  override val pressedStyle = "-fx-font-family: 'Audiowide'; -fx-font-size: 16; -fx-background-color: transparent; -fx-text-fill: white;"
  val focusedStyle = "-fx-font-family: 'Audiowide'; -fx-font-size: 16; -fx-background-color: transparent; -fx-text-fill: transparent;" //  -fx-border-color: black; -fx-border-width: 3px;

  style = defaultStyle

  focused.onChange { (_, _, isFocused) =>
    if (isFocused) {
      style = focusedStyle
    } else {
      style = defaultStyle
    }
  }

  hover.onChange { (_, _, isHovered) =>
    if (isHovered) {
      effect = new Glow(0.8)
      style = hoverStyle
    } else {
      effect = null
      style = if (focused.value) focusedStyle else defaultStyle
    }
  }

  onMousePressed = (_: MouseEvent) => {
    effect = new DropShadow(10, Color.LightGrey)
    style = pressedStyle
  }

  onMouseExited = (_: MouseEvent) => {
    effect = null
    style = if (focused.value) focusedStyle else defaultStyle
  }

  onMouseReleased = (_: MouseEvent) => {
    if (hover.value) {
      effect = new Glow(0.4)
      style = hoverStyle
    } else {
      effect = null
      style = if (focused.value) focusedStyle else defaultStyle
    }
  }
}