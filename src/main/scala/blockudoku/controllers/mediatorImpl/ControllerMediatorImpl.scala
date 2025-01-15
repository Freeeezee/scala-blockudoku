package blockudoku.controllers.mediatorImpl

import blockudoku.commands.Snapshotable
import blockudoku.controllers.{ControllerMediator, ScoreController}
import blockudoku.models.{Element, Tile}
import blockudoku.windows.{FocusManager, FocusState}

class ControllerMediatorImpl(gridController: GridController, elementController: ElementController, focusManager: FocusManager, scoreController: ScoreController) extends ControllerMediator, Snapshotable[?] {
  override def setElement(element: Element, pos: Int) : Unit = {
    if !gridController.setElement(element, pos) then return
    elementController.regenerate(element.slot)
    elementController.resetSelectedElement()
    focusManager.setFocusState(FocusState.Elements)
    val toRemove = scoreController.testGridState()
    gridController.removeTiles(toRemove)
  }
  override def selectElement(element: Element): Unit = {
    elementController.selectElement(element)
    focusManager.setFocusState(FocusState.Grid)
  }
  def createSnapshot(): Unit = {
    gridController.createSnapshot()
    elementController.createSnapshot()
    focusManager.createSnapshot()
    scoreController.createSnapshot()
  }
  def revertSnapshot(): Unit = {
    gridController.revertSnapshot()
    elementController.revertSnapshot()
    focusManager.revertSnapshot()
    scoreController.revertSnapshot()
  }

  override def removeLastSnapshot(): Unit = {
    gridController.removeLastSnapshot()
    elementController.removeLastSnapshot()
    focusManager.removeLastSnapshot()
    scoreController.removeLastSnapshot()
  }
}
