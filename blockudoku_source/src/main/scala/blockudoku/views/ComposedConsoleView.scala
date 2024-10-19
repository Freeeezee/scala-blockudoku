package blockudoku.views

import blockudoku.views.composed.ConsoleElement

case class ComposedConsoleView(element: ConsoleElement, selectedX: Int = 0, selectedY: Int = 0) extends View {

  private val interactableIndices = element.interactableIndices()

  def display(): Unit = {
    println(element.content(highlightedIndex))
  }

  private def highlightedIndex: Int = {
    interactableIndices(selectedY)(selectedX)
  }

  def navigateRight: ComposedConsoleView = {
    if selectedX + 1 < interactableIndices(selectedY).length then {
      copy(selectedX = selectedX + 1)
    }
    else if selectedY + 1 < interactableIndices.length then copy(selectedX = 0, selectedY = selectedY + 1)
    else copy()
  }

  def navigateDown: ComposedConsoleView = {
    if selectedY + 1 < interactableIndices.length then {
      if selectedX < interactableIndices(selectedY + 1).length then copy(selectedY = selectedY + 1)
      else copy(selectedY = selectedY + 1, selectedX = interactableIndices(selectedY).length - 1)
    }
    else copy()
  }

  def navigateLeft: ComposedConsoleView = {
    if selectedX > 0 then copy(selectedX = selectedX - 1)
    else {
      if selectedY > 0 then copy(selectedY = selectedY - 1, selectedX = interactableIndices(selectedY - 1).length - 1)
      else copy()
    }
  }

  def navigateUp: ComposedConsoleView = {
    if selectedY > 0 then {
      if selectedX < interactableIndices(selectedY - 1).length then copy(selectedY = selectedY - 1)
      else copy(selectedY = selectedY - 1, selectedX = interactableIndices(selectedY - 1).length - 1)
    }
    else copy()
  }
}