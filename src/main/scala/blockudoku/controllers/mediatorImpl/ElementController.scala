package blockudoku.controllers.mediatorImpl

import blockudoku.commands.Snapshotable
import blockudoku.controllers.ElementCollector
import blockudoku.models.Element

/**
 * Manages game state related to [[Element]]s.
 * @see [[GridController]] [[ScoreController]]
 */
trait ElementController extends ElementCollector, Snapshotable[ElementController#ElementControllerSnapshot] {
  /**
   * The maximum length (in tiles) of an element.
   */
  val maxElementLength: Int
  /**
   * How many [[Element]]s are selectable at once.
   */
  val elementCount: Int

  /**
   * The currently selectable [[Element]]s.
   */
  var elements: List[Element]

  /**
   * The currently selected [[Element]].
   */
  var selectedElement: Option[Element] = None

  /**
   * Generates a new [[Element]] for the given slot.
   * @param slot The slot to generate the [[Element]] for. Must be in the range of 0 to [[elementCount]].
   * @return The generated [[Element]].
   */
  def regenerate(slot: Int): Element

  /**
   * Selects the given [[Element]].
   * @param element [[Element]] to select.
   */
  def selectElement(element: Element): Unit

  /**
   * Deselects the currently selected [[Element]].
   */
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
