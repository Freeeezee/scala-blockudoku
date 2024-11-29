package blockudoku.commands

import scala.collection.mutable

trait Snapshotable[TSnapshot] {
  val snapshots: mutable.Stack[TSnapshot] = mutable.Stack()
  
  def createSnapshot(): Unit
  def revertSnapshot(): Unit
  
}
