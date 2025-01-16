package blockudoku.views.gui

import scalafx.geometry.{Insets, Pos}
import scalafx.scene.layout.VBox
import scalafx.scene.text.{Font, Text, TextAlignment}
import scalafx.scene.{Node, Scene}

class GuiSettingView(guiLoader: GuiLoader) extends GuiView {

  override def element: Node = {
    new VBox { // root hier notwendig?
      alignment = Pos.Center
      spacing = 10
      prefWidth <== guiLoader.stage.width
      prefHeight <== guiLoader.stage.height

      children = Seq(
        new Text {
          text = "Settings"
          font = Font.loadFont(getClass.getResourceAsStream("/Audiowide-Regular.ttf"), 50)
        },
        new GuiButton("Back", _ => {
          guiLoader.switchToScene(guiLoader.startScene)
        }),
        new GuiButton("Resume", _ => {
          guiLoader.switchToScene(guiLoader.mainScene)
        })
      )
    }
  }
}
