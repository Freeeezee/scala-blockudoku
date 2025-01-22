package blockudoku.commands

import blockudoku.models.Element

/**
 * Factory methods for each supported [[Command]]. It is not recommended to create commands directly.
 * @see [[Command]] [[CommandInvoker]]
 */
trait CommandFactory {
  
  /**
   * Creates a [[Command]] that selects the given element.
   * @param element [[Element]] to select.
   * @return [[Command]] that was created.
   */
  def createSelectElementCommand(element: Element): Command

  /**
   * Creates a [[Command]] that attempts to move the selected element to the given position.
   * @param element [[Element]] to move.
   * @param pos Index of the tile to move the element to.
   * @return [[Command]] that was created.
   */
  def createSetElementCommand(element: Element, pos: Int): Command
}
