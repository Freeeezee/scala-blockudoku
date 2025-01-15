package blockudoku.views.gui

import blockudoku.App
import blockudoku.commands.{CommandFactory, CommandInvoker}
import blockudoku.controllers.mediatorImpl.{ElementController, GridController}
import blockudoku.saving.SaveManager
import blockudoku.services.GridPreviewBuilder
import blockudoku.windows.FocusManager
import scalafx.application.JFXApp3
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.Scene
import scalafx.scene.control.{Button, Label}
import scalafx.scene.layout.{Background, BackgroundImage, BackgroundPosition, BackgroundRepeat, BackgroundSize, VBox}
import scalafx.scene.text.{Font, Text, TextAlignment}
import scalafx.Includes.*
import scalafx.scene.image.{Image, ImageView}

class GuiLoader(commandFactory: CommandFactory, commandInvoker: CommandInvoker, gridController: GridController, elementController: ElementController, focusManager: FocusManager, previewBuilder: GridPreviewBuilder, saveManager: SaveManager) extends JFXApp3 {

  private val viewList = initializeViews()

  private def initializeViews(): List[GuiView] = {
    var views: List[GuiView] = List()

    views = views :+ initializeHeadlineView()
    views = views :+ initializeGridView()
    views = views :+ initializeElementView()
    views = views :+ initialzeUndoRedoView()
    views = views :+ initializeLoadStoreView()
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

  private def initializeLoadStoreView(): GuiView = {
    new GuiLoadStoreView(saveManager)
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
              val sceneTransition = new GuiAnimation()
              sceneTransition.switchScene(this.scene().getRoot, mainScene, stage)
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
            textAlignment = TextAlignment.Center
            wrappingWidth <== width
          },
          new Button {
            text = "Start"
            font = Font.loadFont(getClass.getResourceAsStream("/Audiowide-Regular.ttf"), 20)
            style = "-fx-background-color: #8499B1"
            onAction = _ => {
              val sceneTransition = new GuiAnimation()
              sceneTransition.switchScene(this.scene().getRoot, mainScene, stage) // Parameter sind müll: this, stage, mainScene
              //stage.scene = mainScene
            }
          },
          new Button {
            text = "Settings"
            style = "-fx-background-color: #8499B1"
            font = Font.loadFont(getClass.getResourceAsStream("/Audiowide-Regular.ttf"), 20)
            onAction = _ => {
              val sceneTransition = new GuiAnimation()
              sceneTransition.switchScene(this.scene().getRoot, settingsScene, stage)
              //stage.scene = settingsScene
            }
          },
          new Button {
            text = "Load"
            style = "-fx-background-color: #8499B1"
            font = Font.loadFont(getClass.getResourceAsStream("/Audiowide-Regular.ttf"), 20)
            onAction = _ => {
              saveManager.load()
              val sceneTransition = new GuiAnimation()
              sceneTransition.switchScene(this.scene().getRoot, mainScene, stage)
            }
          }
        )
        background = new Background(Array(new BackgroundImage(
          new Image("file:src/main/resources/background_test.png"),
          BackgroundRepeat.NoRepeat,
          BackgroundRepeat.NoRepeat,
          BackgroundPosition.Center,
          new BackgroundSize(BackgroundSize.Auto, BackgroundSize.Auto, true, false, false, false)
        ))) // BackgroundSize(width, height, widthAsPercentage, heightAsPercentage, contain, cover)
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
} // todo: Start screen auch als Methode nicht innerhalb von start!
// -> simpler background der hervorgehoben wird beim hovern?