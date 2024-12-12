package blockudoku.controllers

import blockudoku.commands.Snapshotable
import blockudoku.models.{Element, Grid}
import blockudoku.observer.Observable
import scalafx.beans.property.ObjectProperty

trait GridController extends Observable, Snapshotable[GridController#GridControllerSnapshot] {
  val grid: ObjectProperty[Grid]
  def setElement(element: Element, selectedPos: Int): Boolean
  case class GridControllerSnapshot(grid: Grid)

  def createSnapshot(): Unit = {
    snapshots.push(GridControllerSnapshot(grid.value))
  }

  def revertSnapshot(): Unit = {
    val snapshot = snapshots.pop()
    grid.value = snapshot.grid
    notifyObservers()
  }
}
