package blockudoku.commands.commandsImpl

import blockudoku.commands.{Command, CommandInvoker}

import scala.collection.mutable

class CommandInvokerImpl extends CommandInvoker {
  private val history: mutable.Stack[Command] = mutable.Stack()
  private val redoStack: mutable.Stack[Command] = mutable.Stack()
  
  def execute(command: Command): Unit = {
    command.execute()
    history push command
    redoStack.clear()
  }
  def undo(): Unit = {
    if history.nonEmpty then {
      val command = history.pop()
      command.undo()
      redoStack push command
    }
  }
  def redo(): Unit = {
    if redoStack.nonEmpty then {
      val command = redoStack.pop()
      command.execute()
      history push command
    }
  }
}
