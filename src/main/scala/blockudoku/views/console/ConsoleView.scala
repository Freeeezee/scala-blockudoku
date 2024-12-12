package blockudoku.views.console

import blockudoku.views.console.composed.ConsoleElement
import blockudoku.windows.{FocusManager, FocusState, Window}

trait ConsoleView(focusManager: FocusManager, window: Window) {
  val interactableFocusStates: Set[FocusState]
  
  def setUpdated(): Unit = {
    window.setUpdated()
  }

  def consoleElement: ConsoleElement
  
  def focused: Boolean = interactableFocusStates.contains(focusManager.focusState)
}
