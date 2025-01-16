package blockudoku.views.gui

import blockudoku.App
import blockudoku.commands.{CommandFactory, CommandInvoker}
import blockudoku.controllers.ScoreController
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

import scala.compiletime.uninitialized

class GuiLoader(commandFactory: CommandFactory, commandInvoker: CommandInvoker, gridController: GridController, elementController: ElementController, focusManager: FocusManager, previewBuilder: GridPreviewBuilder, saveManager: SaveManager, scoreController: ScoreController) extends JFXApp3 {

  private val viewList = initializeViewsMain() // verwendet?

  //private val startScene = initializeViewsStartScreen()

  var currentScene: Scene = uninitialized
  var mainScene: Scene = uninitialized
  var settingsScene: Scene = uninitialized
  var startScene: Scene = uninitialized


  private def initializeViewsMain(): List[GuiView] = {
    var views: List[GuiView] = List()

    views = views :+ initializeHeadlineView()
    views = views :+ initializeGridView()
    views = views :+ initializeElementView()
    views = views :+ initialzeUndoRedoView()
    views = views :+ initializeLoadStoreView()
    views
  }

  private def initializeViewsStartScreen(): List[GuiView] = {
    var views: List[GuiView] = List()

    views = views :+ initializeStartView()
    //views = views :+ initializeLoadStoreView()
    views
    // hier noch patternView hinzufügen???
  }


  private def initializeHeadlineView(): GuiView = {
    new GuiHeadlineView(this, scoreController)
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

  private def initializeStartView(): GuiView = {
    new GuiStartView(this)
  } 
  
  private def intializeSettingView(): GuiView = {
    new GuiSettingView(this)
  }

  private def createMainScene(): Scene = {
    val viewList = initializeViewsMain()
    new Scene(800, 600) {
      root = new VBox {
        alignment = Pos.Center
        prefWidth <== width
        prefHeight <== height
        children = viewList.map(_.element)
      }
    }
  }
  private def createSettingsScene(): Scene = {
    val view = intializeSettingView()
    new Scene(800, 600) {
      root = new VBox {
        alignment = Pos.Center
        prefWidth <== width
        prefHeight <== height
        children = Seq(view.element)
      }
    }
  }
  private def createStartScene(): Scene = {
    val view = initializeViewsStartScreen()
    new Scene(800, 600) {
      root = new VBox {
        alignment = Pos.Center
        prefWidth <== width
        prefHeight <== height
        children = view.map(_.element)
      }
    }
  }

  def switchToScene(newScene: Scene): Unit = {
    val animation = new GuiAnimation()
    animation.switchScene(stage, currentScene, newScene)
    currentScene = newScene

  }

  override def start(): Unit = {

    stage = new JFXApp3.PrimaryStage {

      title.value = "Blockudoku"
      height = 800
      width = 600
      resizable = false


      onCloseRequest = _ => {
        App.exit()
      }
    }
    mainScene = createMainScene()
    settingsScene = createSettingsScene()
    startScene = createStartScene()
    currentScene = startScene

    stage.scene = currentScene

  }
} // todo: Start screen auch als Methode nicht innerhalb von start!
// -> simpler background der hervorgehoben wird beim hovern?