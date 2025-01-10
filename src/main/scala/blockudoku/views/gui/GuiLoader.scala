package blockudoku.views.gui

import blockudoku.App
import blockudoku.commands.{CommandFactory, CommandInvoker}
import blockudoku.controllers.mediatorImpl.{ElementController, GridController}
import blockudoku.services.GridPreviewBuilder
import blockudoku.windows.FocusManager
import scalafx.application.JFXApp3
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.Scene
import scalafx.scene.control.Button
import scalafx.scene.layout.VBox
import scalafx.scene.text.{Font, Text}

class GuiLoader(commandFactory: CommandFactory, commandInvoker: CommandInvoker, gridController: GridController, elementController: ElementController, focusManager: FocusManager, previewBuilder: GridPreviewBuilder) extends JFXApp3 {

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
    new GuiGridView(commandFactory, commandInvoker, gridController, elementController, focusManager, previewBuilder)
  }

  private def initializeElementView(): GuiView = {
    new GuiElementView(commandFactory, commandInvoker, gridController, elementController)
  }

  private def initialzeUndoRedoView(): GuiView = {
    new GuiUndoRedoView(commandInvoker)
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
  private def createSettingsScene(mainScene: Scene): Scene = {
    new Scene {
      root = new VBox() {
        alignment = Pos.Center
        padding = Insets(20)
        children = Seq(
          new Text {
            text = "Settings"
            font = Font.loadFont(getClass.getResourceAsStream("/Audiowide-Regular.ttf"), 50)
          },
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
  }


  override def start(): Unit = {
    val mainScene = createMainScene()

    val settingsScene = createSettingsScene(mainScene)
    val startScene = new Scene {
      //stylesheets.add(getClass.getResource("/styles.css").toExternalForm)
      root = new VBox {
        alignment = Pos.Center
        padding = Insets(20)
        children = Seq(
          new Text {
            text = "Welcome to Blockudoku!"
            font = Font.loadFont(getClass.getResourceAsStream("/Audiowide-Regular.ttf"), 50)
          },
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