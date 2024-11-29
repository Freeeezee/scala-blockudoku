package blockudoku.windows

import blockudoku.commands.Snapshotable

class FocusManager(var focusState: FocusState) extends Snapshotable[FocusManager#FocusManagerSnapshot] {
  def createSnapshot(): Unit = {
    snapshots.push(FocusManagerSnapshot(focusState))
  }
  def revertSnapshot(): Unit = {
    val snapshot = snapshots.pop() 
    focusState = snapshot.focusState
  }
  case class FocusManagerSnapshot(focusState: FocusState)
}
enum FocusState {
  case Grid
  case Elements
}
