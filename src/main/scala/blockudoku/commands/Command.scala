package blockudoku.commands

/**
 * A destructive (but undoable) operation to be executed by a [[blockudoku.commands.CommandInvoker]].
 * @param snapshotables A list of all the objects that need to be snapshot before the command is executed. 
 *                      Is generally provided by the factory.
 *                      @see [[Snapshotable]]
 *                           [[CommandInvoker]]
 *                           [[CommandFactory]]
 */
trait Command(snapshotables : List[Snapshotable[?]]) {
  
  /**
   * Snapshots all necessary objects and then executes the command.
   * Should not be used directly, use [[CommandInvoker.execute]] instead.
   */
  def execute() : Unit = {
    snapshotables.foreach(_.createSnapshot())
    handleExecute()
  }

  /**
   * Reverts all objects to their previous state.
   * Should not be used directly, use [[CommandInvoker.undo]] instead.
   */
  def undo() : Unit = {
    snapshotables.foreach(_.revertSnapshot())
  }
  
  /**
   * The actual implementation of the command.
   */
  protected def handleExecute() : Unit
}
