package blockudoku.controllers

import blockudoku.models.Grid
import blockudoku.observer.Observable

trait GridCollector extends Observable{
  
  def getGrid : Grid

}
