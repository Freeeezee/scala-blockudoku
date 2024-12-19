package blockudoku.windows

import blockudoku.commands.Snapshotable
import blockudoku.observer.Observable

class FocusManager() extends Observable, Snapshotable[FocusManager#FocusManagerSnapshot]{
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
enum FocusState {
  case Grid
  case Elements
}
