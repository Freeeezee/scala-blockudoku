package blockudoku.commands

import scala.collection.mutable

/**
 * Needs to be implemented by all classes that manage mutable state in the command framework.
 * @tparam TSnapshot Type of the snapshot.
 *                   Should be '?' because all snapshots are case classes without common functionality.
 *
 * @see [[Command]]
 */
trait Snapshotable[TSnapshot] {
  /**
   * Recorded snapshots with the most recent snapshot at the top.
   */
  val snapshots: mutable.Stack[TSnapshot] = mutable.Stack()

  /**
   * Creates a snapshot of the current state.
   */
  def createSnapshot(): Unit
  
  /**
   * Reverts the state to the most recent snapshot.
   */
  def revertSnapshot(): Unit
}
