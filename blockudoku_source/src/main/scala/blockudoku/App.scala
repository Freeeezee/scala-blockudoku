package blockudoku

import blockudoku.controllers.{ElementController, GridController}
import blockudoku.input.ConsoleInputHandler
import blockudoku.windows.FocusState.Elements
import blockudoku.windows.{ConsoleWindow, FocusManager}

object App {
  private var exitRequested = false

  def run(): Unit = {
    val window = initializeWindow()

    val inputHandler = new ConsoleInputHandler(window)

    while (!exitRequested) {
      if (window.changed) {
        window.display()
      }

      inputHandler.run()
    }

    inputHandler.close()
  }

  private def initializeWindow(): ConsoleWindow = {
    val focusManager = new FocusManager(focusState = Elements)
    
    val gridController = GridController(9, 9)
    val elementController = ElementController(new RandomImpl(), focusManager)
    
    new ConsoleWindow(gridController, elementController, focusManager)
  }
  
  def exit(): Unit = {
    exitRequested = true
  }
}
