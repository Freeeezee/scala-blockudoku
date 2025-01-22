package blockudoku.controllers

import blockudoku.Observable
import blockudoku.models.Grid

/**
 * Collects [[Grid]] game state models.
 *
 * @see [[ElementCollector]] [[ScoreCollector]]
 */
trait GridCollector extends Observable {

  /**
   * Gets the current [[Grid]].
   * @return the current [[Grid]].
   */
  def getGrid : Grid
}
