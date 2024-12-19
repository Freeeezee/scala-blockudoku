package blockudoku.models

import blockudoku.observer.Observable

trait GridCollector extends Observable{
  
  def getGrid : Grid

}
