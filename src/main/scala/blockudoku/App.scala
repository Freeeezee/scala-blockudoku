package blockudoku

import blockudoku.commands.{CommandFactory, CommandInvoker}
import blockudoku.commands.commandsImpl.{CommandFactoryImpl, CommandInvokerImpl}
import blockudoku.controllers.mediatorImpl.{ControllerMediatorImpl, ElementController, ElementControllerImpl, GridController, GridControllerImpl}
import blockudoku.controllers.{ControllerMediator, GridCollector, GridConfig}
import blockudoku.input.ConsoleInputHandler
import blockudoku.input.consoleInputHandlerImpl.ConsoleInputHandlerImpl
import blockudoku.services.gridPreviewBuilderImpl.GridPreviewBuilderImpl
import blockudoku.services.{ApplicationThread, GridPreviewBuilder, Random, RandomImpl}
import blockudoku.views.gui.GuiLoader
import blockudoku.windows.*
import blockudoku.windows.focusManagerImpl.FocusManagerImpl

object App {


  given Random = RandomImpl()
  given GridConfig = GridConfig()
  given FocusManager = FocusManagerImpl()
  given GridController = GridControllerImpl()
  given ElementController = ElementControllerImpl()
  given ControllerMediator = ControllerMediatorImpl()
  given CommandInvoker = CommandInvokerImpl()
  given CommandFactory = CommandFactoryImpl()
  given GridPreviewBuilder = GridPreviewBuilderImpl()
  given ConsoleWindow()
  given ConsoleInputHandler = ConsoleInputHandlerImpl()


  def run(): Unit = {

    val consoleWindow = ConsoleWindow()

    ApplicationThread().run {
      given GuiLoader()
      val guiWindow = GuiWindow()
      guiWindow.display()
    }
    consoleWindow.display()
  }
  
  def exit(): Unit = {
    System.exit(0)
  }
}
