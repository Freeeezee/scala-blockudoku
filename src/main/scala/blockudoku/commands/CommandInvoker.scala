package blockudoku.commands

import scala.collection.mutable

class CommandInvoker {
  val history: mutable.Stack[Command] = mutable.Stack()
  val redoStack: mutable.Stack[Command] = mutable.Stack()
  
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
