package blockudoku.views.gui

object GuiColorTranslator {
  
  def convertColor (color: blockudoku.models.Colors): scalafx.scene.paint.Color = {
    color match {
      
      case blockudoku.models.Colors.Blue => scalafx.scene.paint.Color.Blue
      case blockudoku.models.Colors.Yellow => scalafx.scene.paint.Color.Yellow
      case blockudoku.models.Colors.Purple => scalafx.scene.paint.Color.Purple
      case blockudoku.models.Colors.Orange => scalafx.scene.paint.Color.Orange
      case blockudoku.models.Colors.Lime => scalafx.scene.paint.Color.Lime
      case blockudoku.models.Colors.Pink => scalafx.scene.paint.Color.Pink
      case blockudoku.models.Colors.Cyan => scalafx.scene.paint.Color.Cyan
      
    }
  }
}
