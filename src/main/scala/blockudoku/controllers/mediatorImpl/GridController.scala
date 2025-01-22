package blockudoku.controllers.mediatorImpl

import blockudoku.commands.Snapshotable
import blockudoku.controllers.GridCollector
import blockudoku.models.{Element, Grid, Tile, TileState}

/**
 * Manage game state related to the [[Grid]].
 * @see [[ElementController]] [[ScoreController]]
 */
trait GridController extends GridCollector, Snapshotable[GridController#GridControllerSnapshot] {
  /**
   * Current grid state.
   */
  var grid: Grid

  /**
   * Attempt to set the given [[Element]] at the given position.
   * @param element [[Element]] to set.
   * @param selectedPos Index of the tile to set the [[Element]] at.
   * @return True if the [[Element]] was successfully set, false otherwise.
   */
  def setElement(element: Element, selectedPos: Int): Boolean

  case class GridControllerSnapshot(grid: Grid)

  def createSnapshot(): Unit = {
    snapshots.push(GridControllerSnapshot(grid))
  }

  def revertSnapshot(): Unit = {
    val snapshot = snapshots.pop()
    grid = snapshot.grid
    notifyObservers()
  }
  
  override def getGrid: Grid = {
    grid
  }
  
  def loadGrid(newGrid : Grid) : Unit = {
    grid = newGrid
    createSnapshot()
    notifyObservers()
  }

  /**
   * Set the given [[Tile]]s empty.
   * @param set [[Tile]]s to set empty.
   */
  def removeTiles(set: Set[Tile]): Unit = {
    grid = grid.copyWithNewState(set.toList, TileState.empty)
    notifyObservers()
  }
}
