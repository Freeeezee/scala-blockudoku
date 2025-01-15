package blockudoku.controllers.mediatorImpl

import blockudoku.commands.Snapshotable
import blockudoku.controllers.ElementCollector
import blockudoku.models.Element
import blockudoku.observer.Observable

trait ElementController extends ElementCollector, Snapshotable[ElementController#ElementControllerSnapshot] {
  val maxElementLength: Int
  val elementCount: Int

  var elements: List[Element]

  var selectedElement: Option[Element] = None
  
  def regenerate(slot: Int): Element
  
  def selectElement(element: Element): Unit
  
  def resetSelectedElement(): Unit = {
    selectedElement = None
  }
  
  case class ElementControllerSnapshot(selectedElement: Option[Element], elements: List[Element])
  
  def createSnapshot(): Unit = {
    snapshots.push(ElementControllerSnapshot(selectedElement, elements))
  }
  
  def revertSnapshot(): Unit = {
    val snapshot = snapshots.pop()
    selectedElement = snapshot.selectedElement
    elements = snapshot.elements
    notifyObservers()
  }

  override def getElements: List[Element] = {
    elements
  }

  override def getSelectedElement: Option[Element] = {
    selectedElement
  }
  
  def loadElements(newElements: List[Element]): Unit = {
    elements = newElements
    createSnapshot()
    notifyObservers()
  }
}
