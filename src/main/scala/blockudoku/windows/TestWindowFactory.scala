package blockudoku.windows

import blockudoku.controllers.{ElementController, GridController}

class TestWindowFactory extends WindowFactory {
  def createWindow(gridController: GridController, elementController: ElementController, focusManager: FocusManager): Window = {
    new TestWindow
  }
}
