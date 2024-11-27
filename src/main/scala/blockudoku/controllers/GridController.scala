package blockudoku.controllers

import blockudoku.models.{Element, Grid}
import blockudoku.observer.Observable

trait GridController extends Observable {
  val grid: Grid
  def setElement(element: Element, selectedPos: Int): Unit
}
