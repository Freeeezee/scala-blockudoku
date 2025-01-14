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

  def convertColor(color: blockudoku.models.Colors): ImageView = {
    color match {
      case blockudoku.models.Colors.Byzantium      => createImageView("file:src/main/resources/block_purple1.png")
      case blockudoku.models.Colors.Blue           => createImageView("file:src/main/resources/block_purple2.png")
      case blockudoku.models.Colors.RaspberryRose  => createImageView("file:src/main/resources/block_purple3.png")
      case blockudoku.models.Colors.Cerise         => createImageView("file:src/main/resources/block_purple4.png")
      case blockudoku.models.Colors.Eminence       => createImageView("file:src/main/resources/block_purple5.png")
      // Weitere Farben hier hinzufügen
    }
  }
}
