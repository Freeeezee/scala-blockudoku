package blockudoku.controllers.scoreImpl

import blockudoku.controllers.{ControllerMediator, GridCollector, ScoreController}
import blockudoku.models.{Grid, Score, Tile, TileState}

import scala.{:+, ::}

class ScoreControllerImpl(gridCollector: GridCollector) extends ScoreController {

  var score: Score = Score(0)

  override def getScore: Int = score.value

  def updateScore(score: Score): Unit = {
    this.score = score
    notifyObservers()
  }

  override def testGridState(): Set[Tile] = {
    val grid = gridCollector.getGrid

    val baseScore = 100

    val rowResult = testRows(grid)
    val colResult = testColumns(grid)

    val multiplied = baseScore * (rowResult.size + colResult.size)

    val toUpdate = rowResult ++ colResult
    val newScore = Score(score.value + multiplied)

    updateScore(newScore)
    toUpdate.flatten
  }

  private def testRows(grid: Grid): Set[Set[Tile]] = {

    var foundSets = Set[Set[Tile]]()

    for(row <- 0 until grid.yLength) {
      var rowSet = Set[Tile]()
      for(col <- 0 until grid.xLength) {
        rowSet = rowSet + grid.tile(col, row).get
      }
      if(testSet(rowSet)) {
        foundSets = foundSets + rowSet
      }
    }
    foundSets
  }

  private def testColumns(grid: Grid): Set[Set[Tile]] = {

    var foundSets = Set[Set[Tile]]()

    for (col <- 0 until grid.xLength) {
      var colSet = Set[Tile]()
      for (row <- 0 until grid.yLength) {
        colSet = colSet + grid.tile(col, row).get
      }
      if (testSet(colSet)) {
        foundSets = foundSets + colSet
      }
    }
    foundSets
  }
  //private def testSquare(): Int = {
  //}

  private def testSet(set: Set[Tile]): Boolean = {
    set.forall(tile => tile.state == TileState.blocked)

  }
}
