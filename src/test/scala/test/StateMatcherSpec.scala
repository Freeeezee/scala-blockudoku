package test

import blockudoku.controllers.{ElementCollector, GridCollector, ScoreCollector}
import blockudoku.models.{Element, Grid}

class StateMatcherSpec extends UnitSpec {
  protected def getCurrentState: State = {
    val scoreCollector = provider.get[ScoreCollector]
    val gridCollector = provider.get[GridCollector]
    val elementCollector = provider.get[ElementCollector]
    
    State(gridCollector.getGrid, scoreCollector.getScore, elementCollector.getElements, elementCollector.getSelectedElement)
  }

  protected def ensureStatesMatch(a: State, b: State): Unit = {
    a shouldEqual b
  }
  
  protected def ensureStatesDontMatch(a: State, b: State): Unit = {
    a shouldNot equal(b)
  }

  protected case class State(grid: Grid, score: Int, list: List[Element], selectedElement: Option[Element])
}
