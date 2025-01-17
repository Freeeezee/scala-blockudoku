package test.commands

import blockudoku.commands.{CommandFactory, CommandInvoker}
import blockudoku.controllers.{ElementCollector, GridCollector}

class SetElementCommandSpec extends CommandsSpec {
  "Set element command" when {
    "trying to set an element at an invalid location" should {
      "not do anything" in {
        val stateBeforeSet = executeFailedSetCommand()

        ensureStatesMatch(stateBeforeSet, getCurrentState)
      }
    }
  }

  private def executeFailedSetCommand(): State = {
    val factory = provider.get[CommandFactory]
    val invoker = provider.get[CommandInvoker]
    val elementCollector = provider.get[ElementCollector]

    val selectCommand = factory.createSelectElementCommand(elementCollector.getElements.head)
    invoker.execute(selectCommand)

    val stateBeforeSet = getCurrentState

    val setCommand = factory.createSetElementCommand(elementCollector.getSelectedElement.get, 8)

    invoker.execute(setCommand)

    stateBeforeSet
  }
}
