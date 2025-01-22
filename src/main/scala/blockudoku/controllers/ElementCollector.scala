package blockudoku.controllers

import blockudoku.Observable
import blockudoku.models.Element

/**
 * Collects [[Element]] game state models.
 *
 * @see [[GridCollector]] [[ScoreCollector]]
 */
trait ElementCollector extends Observable{

  /**
   * Gets all selectable [[Element]].
   * @return a list of [[Element]]s.
   */
  def getElements: List[Element]

  /**
   * Gets the currently selected [[Element]].
   * @return an [[Element]] if any is selected, None otherwise.
   */
  def getSelectedElement: Option[Element]
}