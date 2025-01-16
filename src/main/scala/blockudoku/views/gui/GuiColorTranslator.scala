package blockudoku.views.gui

import blockudoku.services.ColorScheme
import scalafx.scene.image.{Image, ImageView}

object GuiColorTranslator {

  def createImageView(imagePath: String, size: Int): ImageView = {
    new ImageView {
      image = new Image(imagePath)
      fitWidth = size //30
      fitHeight = size
      preserveRatio = true
    }
  }

  def convertColor(index: Int, size: Int): ImageView = {
    createImageView(ColorScheme.getColor(index), size)
  }
}

// case klasse für farben mit liste für strings: statt enum
// -> in element statt color dann index
// Instanz nutzen zur Übergabe der pattern
// stackpane nutzen für previews -> über blocked (&empty)
// Rahmen um grid
// margin / padding für elemente gucken