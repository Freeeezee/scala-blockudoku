package blockudoku

import blockudoku.controllers.{ElementController, GridController}
import blockudoku.services.RandomImpl
import blockudoku.windows.FocusState.Elements
import blockudoku.windows.{ConsoleWindowFactory, FocusManager, TestWindowFactory, Window, WindowFactory}

object App {
  private var exitRequested = false

  def run(): Unit = {
    val windowFactory: WindowFactory = TestWindowFactory()
    
    val window = initializeWindow(windowFactory)
    
    while (!exitRequested) {
      if (window.anyChange()) {
        window.display()
      }
      window.display()
      window.display()
    }
  }

  private def initializeWindow(windowFactory: WindowFactory): Window = {
    val focusManager = new FocusManager(focusState = Elements)

    val elementController = ElementController(new RandomImpl(), focusManager)
    val gridController = GridController(9, 9, elementController, focusManager)
    
    windowFactory.createWindow(gridController, elementController, focusManager)
  }
  
  def exit(): Unit = {
    exitRequested = true
  }
}
