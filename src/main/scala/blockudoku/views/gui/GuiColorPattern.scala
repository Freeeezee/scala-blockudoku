package blockudoku.views.gui


import scalafx.collections.ObservableBuffer
import scalafx.scene.control.ToggleGroup
import javafx.scene.control.RadioButton
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.layout.{HBox, VBox}
import scalafx.Includes.*
import scalafx.geometry.Pos
import scalafx.scene.control
import scalafx.scene.text.Font

class GuiColorPattern extends VBox {

  private val buttonFont: Font = Font.loadFont(getClass.getResourceAsStream("/Audiowide-Regular.ttf"), 20)

  val optionsWithImages = Seq(
    ("Purple", "/preview_purple.png", 0), // purple
    ("Blue", "/preview_blue.png", 1), // blue
    ("Red", "/preview_red.png", 3), // red
    ("Green", "/preview_green.png", 2) // green
  )

  private val newToggleGroup = new ToggleGroup()

  val radioButtons = optionsWithImages.map { case (color, _, index) =>
    new control.RadioButton(color) {
      font = buttonFont
      toggleGroup = GuiColorPattern.this.newToggleGroup
      onAction = { _ =>
        ColorScheme.setColorScheme(index)
      }
    }
  }

  val imageView = new ImageView {
    image = new Image(getClass.getResourceAsStream(optionsWithImages.head._2))
    preserveRatio = true
  }

  newToggleGroup.selectedToggle.onChange { (_, _, newToggle) =>
    val selectedColor = newToggle.asInstanceOf[RadioButton].text.value
    val selectedImagePath = optionsWithImages.find(_._1 == selectedColor).map(_._2).getOrElse("")
    val imageStream = getClass.getResourceAsStream(selectedImagePath)
    if (imageStream != null) {
      imageView.image = new Image(imageStream)
    } else {
      imageView.image = null
    }
  }

  spacing = 10
  alignment = Pos.Center
  children = Seq(
    imageView,
    new HBox {
      alignment = Pos.Center
      spacing = 10
      children = radioButtons
    }
  )
}