package blockudoku.controllers

import blockudoku.models.Element
import blockudoku.observer.Observable

trait ElementCollector extends Observable{

  def getElements: List[Element]
  
  def getSelectedElement: Option[Element]
  
}