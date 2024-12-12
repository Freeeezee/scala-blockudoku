package blockudoku.controllers

import blockudoku.commands.Snapshotable
import blockudoku.models.Element
import blockudoku.windows.{FocusManager, FocusState}

class ControllerMediator(gridController: GridController, elementController: ElementController, focusManager: FocusManager) extends Snapshotable[?] {
  def setElement(element: Element, pos: Int) : Unit = {
    if !gridController.setElement(element, pos) then return
    elementController.regenerate(element.slot)
    elementController.resetSelectedElement()
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
