package blockudoku

import blockudoku.commands.{CommandFactoryImpl, CommandInvoker}
import blockudoku.controllers.{ControllerMediator, ElementController, ElementControllerImpl, GridController, GridControllerImpl}
import blockudoku.services.RandomImpl
import blockudoku.windows.FocusState.Elements
import blockudoku.windows.{ConsoleWindowFactory, FocusManager, GuiWindowFactory, Window, WindowFactory}

object App {
  private var exitRequested = false

  def run(): Unit = {
    val windowFactory: WindowFactory = GuiWindowFactory()
    
    val window = initializeWindow(windowFactory)
    
    while (!exitRequested) {
      if (window.anyChange()) {
        window.display()
      }
      window.handleInput()
      window.display()
      window.display()
    }
  }

  private def initializeWindow(windowFactory: WindowFactory): Window = {
    val focusManager = new FocusManager(focusState = Elements)

    val elementController = ElementControllerImpl(new RandomImpl(), focusManager)
    val gridController = GridControllerImpl(9, 9, elementController, focusManager)
    val mediator = ControllerMediator(gridController, elementController, focusManager)
    val commandFactory = new CommandFactoryImpl(mediator)
    val commandInvoker = new CommandInvoker()
    
    windowFactory.createWindow(commandFactory, commandInvoker, gridController, elementController, focusManager)
  }
  
  def exit(): Unit = {
    exitRequested = true
  }
}
