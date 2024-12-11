package blockudoku.controllers

import blockudoku.commands.Snapshotable
import blockudoku.models.Element
import blockudoku.observer.Observable
import scalafx.beans.property.ObjectProperty

trait ElementController extends Observable, Snapshotable[ElementController#ElementControllerSnapshot] {
  val maxElementLength: Int
  val elementCount: Int

  var elements: ObjectProperty[List[Element]]

  var selectedElement: ObjectProperty[Option[Element]] = ObjectProperty(None)
  
  def regenerate(slot: Int): Element
  
  def selectElement(element: Element): Unit
  
  def resetSelectedElement(): Unit = {
    selectedElement.value = None
  }
  
  case class ElementControllerSnapshot(selectedElement: Option[Element], elements: List[Element])
  
  def createSnapshot(): Unit = {
    snapshots.push(ElementControllerSnapshot(selectedElement.value, elements.value))
  }
  def revertSnapshot(): Unit = {
    val snapshot = snapshots.pop()
    selectedElement.value = snapshot.selectedElement
    elements.value = snapshot.elements
    notifyObservers()
  }
}
