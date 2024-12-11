package blockudoku.windows

import blockudoku.commands.{CommandFactory, CommandInvoker}
import blockudoku.controllers.{ElementController, GridController}
import blockudoku.views.gui.GuiLoader

class GuiWindow(commandFactory: CommandFactory, commandInvoker: CommandInvoker, gridController: GridController, elementController: ElementController) extends Window {

  override def anyChange(): Boolean = true

  override def display(): Unit = {
    val loader = GuiLoader(commandFactory, commandInvoker, gridController, elementController)
    loader.main(Array())
  }

  override def handleInput(): Unit = {

  }
}
