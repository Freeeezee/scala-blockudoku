package blockudoku.views.console

import blockudoku.views.console.composed.ConsoleElement
import blockudoku.windows.{FocusManager, FocusState, Window}
import com.google.inject.Inject

trait ConsoleView @Inject (focusManager: FocusManager, window: Window) {
  val interactableFocusStates: Set[FocusState]
  
  def setUpdated(): Unit = {
    window.setUpdated()
  }

  def consoleElement: ConsoleElement
  
  def focused: Boolean = interactableFocusStates.contains(focusManager.getFocusState)
}
