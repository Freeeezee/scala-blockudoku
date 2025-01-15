package blockudoku.controllers.mediatorImpl

import blockudoku.commands.Snapshotable
import blockudoku.controllers.GridCollector
import blockudoku.models.{Element, Grid}
import blockudoku.observer.Observable


trait GridController extends GridCollector, Snapshotable[GridController#GridControllerSnapshot] {
  var grid: Grid
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
}
