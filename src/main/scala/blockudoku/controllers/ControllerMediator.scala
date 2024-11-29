package blockudoku.controllers

import blockudoku.models.Element
import blockudoku.windows.{FocusManager, FocusState}

trait ControllerMediator {
  def setElement(element: Element, pos: Int) : Unit
  def selectElement(element: Element): Unit
}
