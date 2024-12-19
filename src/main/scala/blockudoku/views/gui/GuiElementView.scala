package blockudoku.views.gui

import blockudoku.commands.{CommandFactory, CommandInvoker}
import blockudoku.models.{ElementCollector, GridCollector}
import blockudoku.observer.Observer
import blockudoku.services.console.ElementFormatter
import scalafx.application.Platform
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.Node
import scalafx.scene.control.Button
import scalafx.scene.layout.HBox

class GuiElementView (commandFactory: CommandFactory, commandInvoker: CommandInvoker, gridCollector: GridCollector, elementCollector: ElementCollector) extends GuiView {

  override def element: Node = {
    new HBox {
      alignment = Pos.Center
      spacing = 10
      padding = Insets(10)
      children = List()
      for index <- elementCollector.getElements.indices do {
        children.add(elementButton(index))
      }
    }
  }

  private def elementButton(index: Int): Node = {
    new Button { // hier weiter
      graphic = elementContent(index)

      minHeight = 100
      minWidth = 100
      elementCollector.addObserver(new Observer {
        override def update(): Unit = {
          Platform.runLater( () => {
            graphic = elementContent(index)
          })
        }
      })
      onAction = _ => {
        val command = commandFactory.createSelectElementCommand(elementCollector.getElements(index))
        commandInvoker.execute(command)
      }
    }
  }

  private def elementContent(index: Int): Node = {
    val element = elementCollector.getElements(index)
    GuiElementFormatter(element).content
  }
}
