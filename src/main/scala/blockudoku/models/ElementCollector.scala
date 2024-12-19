package blockudoku.models

import blockudoku.observer.Observable

trait ElementCollector extends Observable{

  def getElements: List[Element]
  
  def getSelectedElement: Option[Element]
}