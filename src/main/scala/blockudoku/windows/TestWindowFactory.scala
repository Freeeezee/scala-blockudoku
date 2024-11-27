package blockudoku.windows

import blockudoku.controllers.{ControllerMediator, ElementController, GridController}

class TestWindowFactory extends WindowFactory {
  def createWindow(mediator: ControllerMediator, gridController: GridController, elementController: ElementController, focusManager: FocusManager): Window = {
    new TestWindow
  }
}
