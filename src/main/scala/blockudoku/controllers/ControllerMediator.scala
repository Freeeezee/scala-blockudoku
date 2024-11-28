package blockudoku.controllers

import blockudoku.models.Element
import blockudoku.windows.{FocusManager, FocusState}

class ControllerMediator(gridController: GridController, elementController: ElementController, focusManager: FocusManager) {
  def setElement(element: Element, pos: Int) : Unit = {
    gridController.setElement(element, pos)
    elementController.regenerate(element.slot)
    focusManager.focusState = FocusState.Elements
  }
  def selectElement(element: Element): Unit = {
    elementController.selectElement(element)
    focusManager.focusState = FocusState.Grid
  }
  def createSnapshot(): Unit = {
    gridController.createSnapshot()
    elementController.createSnapshot()
    focusManager.createSnapshot()
  }
  def revertSnapshot(): Unit = {
    gridController.revertSnapshot()
    elementController.revertSnapshot()
    focusManager.revertSnapshot()
  }
}
