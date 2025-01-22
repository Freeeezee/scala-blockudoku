package blockudoku.windows.focusManagerImpl

import blockudoku.Observable
import blockudoku.commands.Snapshotable
import blockudoku.windows.{FocusManager, FocusState}

class FocusManagerImpl extends FocusManager, Snapshotable[FocusManagerImpl#FocusManagerSnapshot] {
  private var focusState: FocusState = FocusState.Elements

  def setFocusState(focusState: FocusState): Unit = {
    this.focusState = focusState
    notifyObservers()
  }
  
  def getFocusState: FocusState = focusState
  
  def createSnapshot(): Unit = {
    snapshots.push(FocusManagerSnapshot(focusState))
  }
  def revertSnapshot(): Unit = {
    val snapshot = snapshots.pop()
    focusState = snapshot.focusState
    notifyObservers()
  }

  case class FocusManagerSnapshot(focusState: FocusState)
}
