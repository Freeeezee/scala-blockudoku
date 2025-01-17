package blockudoku.views.gui

import blockudoku.services.ColorScheme
import scalafx.scene.image.{Image, ImageView}

object GuiColorTranslator {

  def createImageView(imagePath: String, size: Int): ImageView = {
    new ImageView {
      image = new Image(imagePath)
      fitWidth = size
      fitHeight = size
      preserveRatio = true
    }
  }

  def convertColor(index: Int, size: Int): ImageView = {
    createImageView(ColorScheme.getColor(index), size)
  }
}