package blockudoku.commands

/**
 * Responsible for executing, undoing and redoing commands.
 * @see [[Command]] [[CommandFactory]]
 */
trait CommandInvoker {
  
  /**
   * Executes a command.
   * @param command the command to execute.
   */
  def execute(command: Command): Unit
  
  /**
   * Undoes the last command executed.
   */
  def undo(): Unit
  
  /**
   * Redoes the last undone command.
   */
  def redo(): Unit
}
