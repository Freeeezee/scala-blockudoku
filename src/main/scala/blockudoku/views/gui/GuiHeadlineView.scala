package blockudoku.views.gui
import blockudoku.controllers.ScoreCollector
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.Node
import scalafx.scene.layout.*
import scalafx.scene.text.{Font, FontWeight, Text}

class GuiHeadlineView(guiLoader: GuiLoader, scoreCollector: ScoreCollector) extends GuiView {


  override def element: Node = {
    new BorderPane {
      top = new HBox {
      alignment = Pos.Center
      spacing = 10
      padding = Insets(10)
      children = Seq(
        new GuiButton("Back", _ => {
          guiLoader.switchToScene(guiLoader.startScene)
        }),
        new Region {
          hgrow = Priority.Always
        },
        new GuiButton("Settings", _ => {
          guiLoader.switchToScene(guiLoader.settingsScene)
        })
      )
    }
      center = new VBox {
        alignment = Pos.Center
        children = Seq(
          new Text {
            text = "Blockudoku"
            font = Font.font("Audiowide", FontWeight.Bold, 50)
          },
          new Text {
            text = "Score: " + scoreCollector.getScore

            font = Font.font("Audiowide", FontWeight.Bold, 40)

            var lastScore = scoreCollector.getScore

            scoreCollector.addObserver(() => {
              text = "Score: " + scoreCollector.getScore
              if (lastScore != scoreCollector.getScore) {
                lastScore = scoreCollector.getScore
                val animation = new GuiAnimation().scoreAnimation(this)
                animation.play()
              }
            })
          }
        )
      }
    }
  }
}