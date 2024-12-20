package blockudoku.windows

import blockudoku.commands.Snapshotable
import blockudoku.observer.Observable

trait FocusManager extends Observable, Snapshotable[?] {
  def setFocusState(focusState: FocusState): Unit
  def getFocusState: FocusState
}

enum FocusState {
  case Grid
  case Elements
}