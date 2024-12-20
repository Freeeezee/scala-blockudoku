package blockudoku.controllers.mediatorImpl

import blockudoku.commands.Snapshotable
import blockudoku.controllers.ControllerMediator
import blockudoku.models.Element
import blockudoku.windows.{FocusManager, FocusState}

class ControllerMediatorImpl(gridController: GridController, elementController: ElementController, focusManager: FocusManager) extends ControllerMediator, Snapshotable[?] {
  override def setElement(element: Element, pos: Int) : Unit = {
    if !gridController.setElement(element, pos) then return
    elementController.regenerate(element.slot)
    elementController.resetSelectedElement()
    focusManager.setFocusState(FocusState.Elements)
  }
  override def selectElement(element: Element): Unit = {
    elementController.selectElement(element)
    focusManager.setFocusState(FocusState.Grid)
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
