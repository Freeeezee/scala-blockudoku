package blockudoku.windows

import blockudoku.commands.{CommandFactory, CommandInvoker}
import blockudoku.controllers.mediatorImpl.{ElementController, GridController}
import blockudoku.views.gui.GuiLoader

class GuiWindow(guiLoader: GuiLoader) extends Window {
  
  override def display(): Unit = {
    guiLoader.main(Array())
  }
  
  override def setUpdated(): Unit = {
    // Not needed
  }
}
