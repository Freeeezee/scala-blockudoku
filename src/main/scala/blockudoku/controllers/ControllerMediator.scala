package blockudoku.controllers

import blockudoku.commands.Snapshotable
import blockudoku.models.Element

trait ControllerMediator extends Snapshotable[?] {
  def setElement(element: Element, pos: Int) : Unit
  def selectElement(element: Element): Unit
}
