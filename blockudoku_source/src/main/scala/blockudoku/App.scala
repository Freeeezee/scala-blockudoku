package blockudoku

import blockudoku.controllers.{ElementController, GridController}
import blockudoku.input.{ConsoleInputHandler, ConsoleReaderImpl}
import blockudoku.views.console.composed.Direction.*
import blockudoku.windows.FocusState.Elements
import blockudoku.windows.{ConsoleWindow, FocusManager}

object App {
  private var exitRequested = false

  def run(): Unit = {
    val window = initializeWindow()

    val inputHandler = new ConsoleInputHandler(new ConsoleReaderImpl())

    initializeEvents(inputHandler, window)
    
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
  
  private def initializeEvents(handler: ConsoleInputHandler, window: ConsoleWindow): Unit = {
    handler.arrowUpKey.addListener(() => window.navigate(Up))
    handler.arrowDownKey.addListener(() => window.navigate(Down))
    handler.arrowLeftKey.addListener(() => window.navigate(Left))
    handler.arrowRightKey.addListener(() => window.navigate(Right))
    
    handler.enterKey.addListener(() => window.select())
    
    handler.qKey.addListener(() => exit())
  }
  
  private def exit(): Unit = {
    exitRequested = true
  }
}
