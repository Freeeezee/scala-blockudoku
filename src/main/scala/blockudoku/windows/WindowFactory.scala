package blockudoku.windows

import blockudoku.controllers.{ElementController, GridController}

abstract class WindowFactory {
  def createWindow(gridController: GridController, elementController: ElementController, focusManager: FocusManager): Window
}
