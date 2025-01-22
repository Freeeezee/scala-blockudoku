package blockudoku.views.gui

import blockudoku.Observer
import blockudoku.commands.{CommandFactory, CommandInvoker}
import blockudoku.controllers.{ElementCollector, GridCollector}
import scalafx.application.Platform
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.Node
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
    new GuiElementButton("", _ => {
      val command = commandFactory.createSelectElementCommand(elementCollector.getElements(index))
      commandInvoker.execute(command)
    }) {
      graphic = elementContent(index)

      minHeight = 100
      minWidth = 100
      elementCollector.addObserver(() => {
        Platform.runLater(() => {
          graphic = elementContent(index)
        })
      })
      ColorScheme.addObserver(() => {
        graphic = elementContent(index)
      })
    }
  }

  private def elementContent(index: Int): Node = {
    val element = elementCollector.getElements(index)
    GuiElementFormatter(element).content
  }
}
