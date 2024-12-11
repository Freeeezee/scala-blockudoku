package blockudoku.views.gui

import blockudoku.commands.{CommandFactory, CommandInvoker}
import blockudoku.controllers.{ElementController, GridController}
import blockudoku.services.console.ElementFormatter
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.Node
import scalafx.scene.control.Button
import scalafx.scene.layout.HBox

class GuiElementView (commandFactory: CommandFactory, commandInvoker: CommandInvoker, gridController: GridController, elementController: ElementController) extends GuiView {

  override def element: Node = {
    new HBox {
      alignment = Pos.Center
      children = List()
      for index <- elementController.elements.value.indices do {
        children.add(elementButton(index))
      }
    }
  }
  private def elementButton(index: Int): Node = {
    new Button {
      text = elementContent(index)
      minHeight = 100
      minWidth = 100
      elementController.elements.onChange { (_, _, _) =>
        text = elementContent(index)
      }
      onAction = _ => {
        val command = commandFactory.createSelectElementCommand(elementController.elements.value(index))
        commandInvoker.execute(command)
      }
    }
  }
  private def elementContent(index: Int): String = {
    val element = elementController.elements.value(index)
    ElementFormatter(element).content
  }
}
