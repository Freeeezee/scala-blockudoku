package test.commands

import blockudoku.commands.{CommandFactory, CommandInvoker}
import blockudoku.controllers.{ElementCollector, GridCollector, ScoreCollector}
import blockudoku.models.{Element, Grid}

import scala.compiletime.uninitialized

class UndoRedoSpec extends CommandsSpec {
  private var initialState: State = uninitialized
  private var stateAfterOneCommand: State = uninitialized

  "A command" should {
    "be undoable" in {
      executeCommands()

      val invoker = provider.get[CommandInvoker]
      invoker.undo()

      ensureStateMatches(getCurrentState, stateAfterOneCommand)
    }

    "be undoable multiple times" in {
      executeCommands()

      val invoker = provider.get[CommandInvoker]
      invoker.undo()
      invoker.undo()

      ensureStateMatches(getCurrentState, initialState)
    }

    "be redoable" in {
      executeCommands()

      val state = getCurrentState

      val invoker = provider.get[CommandInvoker]
      invoker.undo()
      invoker.redo()

      ensureStateMatches(getCurrentState, state)
    }
  }

  private def executeCommands(): Unit = {
    initialState = getCurrentState

    val factory = provider.get[CommandFactory]
    val invoker = provider.get[CommandInvoker]
    val elementCollector = provider.get[ElementCollector]

    val selectCommand = factory.createSelectElementCommand(elementCollector.getElements.head)
    invoker.execute(selectCommand)

    stateAfterOneCommand = getCurrentState

    val setCommand = factory.createSetElementCommand(elementCollector.getSelectedElement.get, 0)

    invoker.execute(setCommand)
  }

  private def getCurrentState: State = {
    val scoreCollector = provider.get[ScoreCollector]
    val gridCollector = provider.get[GridCollector]
    val elementCollector = provider.get[ElementCollector]

    State(gridCollector.getGrid, scoreCollector.getScore, elementCollector.getElements, elementCollector.getSelectedElement)
  }

  private def ensureStateMatches(a: State, b: State): Unit = {
    a shouldEqual b
  }

  private case class State(grid: Grid, score: Int, list: List[Element], selectedElement: Option[Element])
}
