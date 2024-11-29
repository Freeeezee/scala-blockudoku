package blockudoku.windows

import blockudoku.commands.{CommandFactory, CommandInvoker}
import blockudoku.controllers.{ControllerMediator, ElementController, GridController}

abstract class WindowFactory {
  def createWindow(commandFactory: CommandFactory, commandInvoker: CommandInvoker, gridController: GridController, elementController: ElementController, focusManager: FocusManager): Window
}
