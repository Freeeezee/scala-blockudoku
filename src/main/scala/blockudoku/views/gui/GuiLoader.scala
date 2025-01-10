package blockudoku.views.gui

import blockudoku.App
import blockudoku.commands.{CommandFactory, CommandInvoker}
import blockudoku.controllers.mediatorImpl.{ElementController, GridController}
import blockudoku.services.GridPreviewBuilder
import blockudoku.windows.FocusManager
import com.google.inject.Inject
import scalafx.application.JFXApp3
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.Scene
import scalafx.scene.control.Button
import scalafx.scene.layout.{StackPane, VBox}
import scalafx.scene.text.{Font, Text}
import scalafx.stage.Stage

class GuiLoader (using commandFactory: CommandFactory, commandInvoker: CommandInvoker, gridController: GridController, elementController: ElementController, focusManager: FocusManager, previewBuilder: GridPreviewBuilder) extends JFXApp3 {

  private val viewList = initializeViews()

  private def initializeViews(): List[GuiView] = {
    var views: List[GuiView] = List()

    views = views :+ initializeHeadlineView()
    views = views :+ initializeGridView()
    views = views :+ initializeElementView()
    views = views :+ initialzeUndoRedoView()
    views
  }

  private def initializeHeadlineView(): GuiView = {
    new GuiHeadlineView()
  }

  private def initializeGridView(): GuiView = {
    new GuiGridView()
  }

  private def initializeElementView(): GuiView = {
    new GuiElementView()
  }

  private def initialzeUndoRedoView(): GuiView = {
    new GuiUndoRedoView()
  }

  private def createMainScene(): Scene = {
    val viewList = initializeViews()
    new Scene {
      //stylesheets.add(getClass.getResource("/styles.css").toExternalForm) // CSS global laden
      root = new VBox {
        alignment = Pos.Center
        padding = Insets(20)
        children = viewList.map(_.element)
      }
    }
  }


  override def start(): Unit = {
    val mainScene = createMainScene()

    val settingsScene = new Scene {
      root = new VBox() {
        children = Seq(
          new Button {
            text = "Back"
            style = "-fx-background-color: #8499B1"
            font = Font.loadFont(getClass.getResourceAsStream("/Audiowide-Regular.ttf"), 20)
            onAction = _ => {
              stage.scene = mainScene
            }
          }
        )
      }
    }
    val startScene = new Scene {
      //stylesheets.add(getClass.getResource("/styles.css").toExternalForm)
      root = new VBox {
        alignment = Pos.Center
        padding = Insets(20)
        children = Seq(
          new Button {
            text = "Start"
            font = Font.loadFont(getClass.getResourceAsStream("/Audiowide-Regular.ttf"), 20)
            style = "-fx-background-color: #8499B1"
            onAction = _ => {
              stage.scene = mainScene
            }
          },
        new Button {
          text = "settings"
          style = "-fx-background-color: #8499B1"
          font = Font.loadFont(getClass.getResourceAsStream("/Audiowide-Regular.ttf"), 20)
          onAction = _ => {
            stage.scene = settingsScene
          }
        },
        )
      }
    }

    stage = new JFXApp3.PrimaryStage {
      title.value = "Blockudoku"
      height = 800
      width = 600
      scene = startScene
      onCloseRequest = _ => {
        App.exit()
      }
    }
  }
}
