package blockudoku.controllers

import blockudoku.commands.Snapshotable
import blockudoku.models.{Score, Tile}

trait ScoreController extends ScoreCollector, Snapshotable[ScoreController#ScoreControllerSnapshot]{
  
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

  def testGridState(): Set[Tile]
}