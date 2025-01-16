package blockudoku.views.gui

import scalafx.geometry.{Insets, Pos}
import scalafx.scene.layout.{HBox, VBox}
import scalafx.scene.text.{Font, Text, TextAlignment}
import scalafx.scene.{Node, Scene}

class GuiSettingView(guiLoader: GuiLoader) extends GuiView {

  override def element: Node = {
    new VBox {
      alignment = Pos.Center
      spacing = 10
      prefWidth <== guiLoader.stage.width
      prefHeight <== guiLoader.stage.height

      children = Seq(
        new VBox {
          alignment = Pos.TopCenter
          children = Seq(
            new Text {
              text = "Settings"
              font = Font.loadFont(getClass.getResourceAsStream("/Audiowide-Regular.ttf"), 50)
            }
          )
        },
        new HBox {
          alignment = Pos.Center
          children = Seq(
            new GuiColorPattern
          )
        },
        new HBox {
          alignment = Pos.Center
          spacing = 10
          children = Seq(
            new GuiButton("Resume", _ => {
              guiLoader.switchToScene(guiLoader.mainScene)
            }),
            new GuiButton("Back", _ => {
              guiLoader.switchToScene(guiLoader.startScene)
            })
          )
        }
      )
    }
  }
}