package blockudoku.views.console

import blockudoku.views.console.composed.ConsoleElement
import blockudoku.windows.{FocusManager, FocusState}

trait ConsoleView(focusManager: FocusManager) {
  val interactableFocusStates: Set[FocusState]
  
  def consoleElement: ConsoleElement
  
  def focused: Boolean = interactableFocusStates.contains(focusManager.focusState)
}
