package blockudoku

import blockudoku.commands.{CommandFactoryImpl, CommandInvoker}
import blockudoku.controllers.{ControllerMediator, ElementControllerImpl, GridControllerImpl}
import blockudoku.services.{ApplicationThread, RandomImpl}
import blockudoku.windows.FocusState.Elements
import blockudoku.windows.*
import scalafx.application.Platform

object App {
  
  val focusManager = new FocusManager(focusState = Elements)

  val elementController = ElementControllerImpl(new RandomImpl(), focusManager)
  val gridController = GridControllerImpl(9, 9, elementController, focusManager)
  val mediator = ControllerMediator(gridController, elementController, focusManager)
  val commandFactory = new CommandFactoryImpl(mediator)
  val commandInvoker = new CommandInvoker()

  def run(): Unit = {
    val guiFactory: WindowFactory = GuiWindowFactory()
    val consoleFactory: WindowFactory = ConsoleWindowFactory()
    
    val guiWindow = initializeWindow(guiFactory)
    val consoleWindow = initializeWindow(consoleFactory)

    ApplicationThread().run {
      guiWindow.display()
    }
    
    consoleWindow.display()
  }

  private def initializeWindow(windowFactory: WindowFactory): Window = {
    windowFactory.createWindow(commandFactory, commandInvoker, gridController, elementController, focusManager)
  }
  
  def exit(): Unit = {
    System.exit(0)
  }
}
