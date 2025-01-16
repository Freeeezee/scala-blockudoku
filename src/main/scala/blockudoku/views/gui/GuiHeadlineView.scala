package blockudoku.views.gui
import blockudoku.controllers.{ScoreCollector, ScoreController}
import blockudoku.controllers.scoreImpl.ScoreControllerImpl
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.Node
import scalafx.scene.layout.{BorderPane, HBox, Priority, Region, VBox}
import scalafx.scene.text.{Font, FontWeight, Text}
import scalafx.Includes.*

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

            scoreCollector.addObserver(() => {
              text = "Score: " + scoreCollector.getScore
            })
          }
        )
      }
      //BorderPane.setAlignment(top.value, Pos.TopLeft)
      //BorderPane.setAlignment(center.value, Pos.Center)
    }
  }
}