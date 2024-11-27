package blockudoku.controllers

import blockudoku.models.Element
import blockudoku.observer.Observable

trait ElementController extends Observable {
  val maxElementLength: Int
  val elementCount: Int

  val elements: Array[Element]

  var selectedElement: Option[Element] = None
  
  def regenerate(slot: Int): Element
  
  def selectElement(element: Element): Unit
}
