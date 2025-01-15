package blockudoku.views.gui

import javafx.event.EventHandler
import javafx.event.ActionEvent
import scalafx.scene.control.Button
import scalafx.scene.text.Font

class GuiButton(buttonText : String, action : EventHandler[ActionEvent]) extends Button {
  
  style = "-fx-background-color: #8499B1"
  font = Font.loadFont(getClass.getResourceAsStream("/Audiowide-Regular.ttf"), 20)
  text = buttonText
  onAction = action
}
