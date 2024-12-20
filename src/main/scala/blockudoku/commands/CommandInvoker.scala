package blockudoku.commands

trait CommandInvoker {
  def execute(command: Command): Unit
  def undo(): Unit
  def redo(): Unit
}
