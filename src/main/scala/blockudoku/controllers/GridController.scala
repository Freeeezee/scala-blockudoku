package blockudoku.controllers

import blockudoku.commands.Snapshotable
import blockudoku.models.{Element, Grid}
import blockudoku.observer.Observable

trait GridController extends Observable, Snapshotable[GridController#GridControllerSnapshot] {
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
}
