package blockudoku.views.gui

import blockudoku.commands.{CommandFactory, CommandInvoker}
import blockudoku.controllers.{ElementController, GridController}
import scalafx.Includes.*
import scalafx.application.JFXApp3
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.Scene
import scalafx.scene.layout.VBox

class GuiLoader(commandFactory: CommandFactory, commandInvoker: CommandInvoker, gridController: GridController, elementController: ElementController) extends JFXApp3 {

  private val viewList = initializeViews()

  private def initializeViews(): List[GuiView] = {
    var views: List[GuiView] = List()

    views = views :+ initializeHeadlineView()
    views = views :+ initializeGridView()
    views = views :+ initializeElementView()
    views
  }

  private def initializeHeadlineView(): GuiView = {
    new GuiHeadlineView()
  }

  private def initializeGridView(): GuiView = {
    new GuiGridView(commandFactory, commandInvoker, gridController, elementController)
  }

  private def initializeElementView(): GuiView = {
    new GuiElementView(commandFactory, commandInvoker, gridController, elementController)
  }


  override def start(): Unit = {
    stage = new JFXApp3.PrimaryStage {
      title.value = "Blockudoku"
      scene = new Scene(800, 600) {
        content = new VBox {
          alignment = Pos.Center
          padding = Insets(20)
          children = viewList.map(_.element)
        }
      }
    }
  }
}
