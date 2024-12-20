package blockudoku.windows

import blockudoku.commands.{CommandFactory, CommandInvoker}
import blockudoku.controllers.{ElementController, GridController}
import blockudoku.views.gui.GuiLoader

class GuiWindow(commandFactory: CommandFactory, commandInvoker: CommandInvoker, gridController: GridController, elementController: ElementController, focusManager: FocusManager) extends Window {
  
  override def display(): Unit = {
    val loader = GuiLoader(commandFactory, commandInvoker, gridController, elementController, focusManager)
    loader.main(Array())
  }
  
  override def setUpdated(): Unit = {
    // Not needed
  }
}
