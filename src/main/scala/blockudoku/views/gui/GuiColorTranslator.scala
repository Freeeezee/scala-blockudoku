package blockudoku.views.gui

import scalafx.scene.image.{Image, ImageView}

object GuiColorTranslator {

  def createImageView(imagePath: String): ImageView = {
    new ImageView {
      image = new Image(imagePath)
      fitWidth = 30
      fitHeight = 30
      preserveRatio = true
    }
  }

  def convertColor(index: Int): ImageView = {
    createImageView(ColorScheme.getColor(index))
  }
}

// case klasse für farben mit liste für strings: statt enum
// -> in element statt color dann index
// Instanz nutzen zur Übergabe der pattern
// stackpane nutzen für previews -> über blocked (&empty)
// Rahmen um grid
// margin / padding für elemente gucken