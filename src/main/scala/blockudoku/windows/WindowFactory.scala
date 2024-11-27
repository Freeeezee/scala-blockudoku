package blockudoku.windows

import blockudoku.controllers.{ControllerMediator, ElementController, GridController}

abstract class WindowFactory {
  def createWindow(mediator: ControllerMediator, gridController: GridController, elementController: ElementController, focusManager: FocusManager): Window
}
