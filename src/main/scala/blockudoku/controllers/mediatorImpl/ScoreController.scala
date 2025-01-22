package blockudoku.controllers.mediatorImpl

import blockudoku.commands.Snapshotable
import blockudoku.controllers.ScoreCollector
import blockudoku.models.{Score, Tile}

/**
 * Manage game state related to the [[Score]].
 * @see [[GridController]] [[ElementController]]
 */
trait ScoreController extends ScoreCollector, Snapshotable[ScoreController#ScoreControllerSnapshot]{

  /**
   * Current score.
   */
  var score: Score
  
  case class ScoreControllerSnapshot(score: Score)

  override def createSnapshot(): Unit = {
    snapshots.push(ScoreControllerSnapshot(score))
  }
  
  override def revertSnapshot(): Unit = {
    val snapshot = snapshots.pop()
    score = snapshot.score
    notifyObservers()
  }

  /**
   * Check the current grid state for win conditions and update the score accordingly.
   * @return Set of [[Tile]]s that should be removed from the grid.
   */
  def testGridState(): Set[Tile]
  
  def loadScore(newScore: Int): Unit = {
    score = Score(newScore)
    createSnapshot()
    notifyObservers()
  }
}