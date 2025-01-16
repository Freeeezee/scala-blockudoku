package test.commands

import blockudoku.commands.{CommandFactory, CommandInvoker}
import blockudoku.controllers.ElementCollector

import scala.compiletime.uninitialized

class UndoRedoSpec extends CommandsSpec {
  private var initialState: State = uninitialized
  private var stateAfterOneCommand: State = uninitialized

  "A command" should {
    "be undoable" in {
      executeCommands()

      val invoker = provider.get[CommandInvoker]
      invoker.undo()

      ensureStatesMatch(getCurrentState, stateAfterOneCommand)
    }

    "be undoable multiple times" in {
      executeCommands()

      val invoker = provider.get[CommandInvoker]
      invoker.undo()
      invoker.undo()

      ensureStatesMatch(getCurrentState, initialState)
    }

    "be redoable" in {
      executeCommands()

      val state = getCurrentState

      val invoker = provider.get[CommandInvoker]
      invoker.undo()
      invoker.redo()

      ensureStatesMatch(getCurrentState, state)
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
}
