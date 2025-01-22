package blockudoku.controllers

import blockudoku.commands.Snapshotable
import blockudoku.models.Element

/**
 * The central interface for making destructive changes to the game state.
 * Communicates to more specified controllers.
 */
trait ControllerMediator extends Snapshotable[?] {

  /**
   * Attempts to place the given element at the given position.
   * @param element [[Element]] to place.
   * @param pos Index of the tile to place the element at.
   */
  def setElement(element: Element, pos: Int) : Unit

  /**
   * Selects the given element.
   * @param element [[Element]] to select.
   */
  def selectElement(element: Element): Unit
}
