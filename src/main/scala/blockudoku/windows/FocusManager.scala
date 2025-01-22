package blockudoku.windows

import blockudoku.Observable
import blockudoku.commands.Snapshotable

trait FocusManager extends Observable, Snapshotable[?] {
  def setFocusState(focusState: FocusState): Unit
  def getFocusState: FocusState
}

enum FocusState {
  case Grid
  case Elements
}