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
import scalafx.scene.layout.VBox

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


  override def start(): Unit = {
    stage = new JFXApp3.PrimaryStage {
      title.value = "Blockudoku"
      height = 800
      width = 600
      scene = new Scene {
        root = new VBox {
          alignment = Pos.Center
          padding = Insets(20)
          children = viewList.map(_.element)
        }
      }
      onCloseRequest = _ => {
        App.exit()
      }
    }
  }
}
