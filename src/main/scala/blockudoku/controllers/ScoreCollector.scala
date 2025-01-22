package blockudoku.controllers

import blockudoku.Observable

trait ScoreCollector extends Observable {
  
  def getScore: Int

}
