package blockudoku.windows

import blockudoku.App
import blockudoku.commands.{CommandFactory, CommandInvoker}
import blockudoku.controllers.{ControllerMediator, ElementController, GridController}
import blockudoku.input.ConsoleInputHandler
import blockudoku.views.console.composed.Direction.*

class ConsoleWindowFactory extends WindowFactory {
  
  override def createWindow(commandFactory: CommandFactory, commandInvoker: CommandInvoker, gridController: GridController, elementController: ElementController, focusManager: FocusManager): Window = {
    val inputHandler = new ConsoleInputHandler()
    val window = ConsoleWindow(commandFactory, commandInvoker, gridController, elementController, focusManager, inputHandler)
    initializeEvents(inputHandler, window)
    window
  }
  
  
  private def initializeEvents(handler: ConsoleInputHandler, window: ConsoleWindow): Unit = {
    handler.arrowUpKey.addListener(() => window.navigate(Up))
    handler.arrowDownKey.addListener(() => window.navigate(Down))
    handler.arrowLeftKey.addListener(() => window.navigate(Left))
    handler.arrowRightKey.addListener(() => window.navigate(Right))
    
    handler.zKey.addListener(() => window.undo())
    handler.rKey.addListener(() => window.redo())

    handler.enterKey.addListener(() => window.select())

    handler.qKey.addListener(() => App.exit())
  }
}
