package blockudoku.controllers

import blockudoku.models.Element
import blockudoku.windows.{FocusManager, FocusState}

class ControllerMediatorImpl(gridController: GridController, elementController: ElementController, focusManager: FocusManager) extends ControllerMediator {

  override def setElement(element: Element, pos: Int): Unit = {
    gridController.setElement(element, pos)
    elementController.regenerate(element.slot)
    focusManager.focusState = FocusState.Elements
  }
  
  override def selectElement(element: Element): Unit = {
    elementController.selectElement(element)
    focusManager.focusState = FocusState.Grid
  }
}
