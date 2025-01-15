package blockudoku.controllers

import blockudoku.observer.Observable

trait ScoreCollector extends Observable {
  
  def getScore: Int

}
