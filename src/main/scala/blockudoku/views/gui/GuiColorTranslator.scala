package blockudoku.views.gui

object GuiColorTranslator {
  
  def convertColor (color: blockudoku.models.Colors): scalafx.scene.paint.Color = {
    color match {
      
      case blockudoku.models.Colors.Byzantium => scalafx.scene.paint.Color.web("#6B2D5C")
      case blockudoku.models.Colors.Blue => scalafx.scene.paint.Color.web("#348AA7")
      case blockudoku.models.Colors.RaspberryRose => scalafx.scene.paint.Color.web("AE3364")
      case blockudoku.models.Colors.Cerise => scalafx.scene.paint.Color.web("#F0386B")
      case blockudoku.models.Colors.Eminence => scalafx.scene.paint.Color.web("#783F8E")
      case blockudoku.models.Colors.BrightPink => scalafx.scene.paint.Color.web("#FF5376")
      case blockudoku.models.Colors.CoralPink => scalafx.scene.paint.Color.web("F18B83")

    }
  }
}
