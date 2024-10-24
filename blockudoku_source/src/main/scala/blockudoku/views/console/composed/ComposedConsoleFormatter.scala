package blockudoku.views.console.composed

import scala.util.Try

case class ComposedConsoleFormatter(element: ConsoleElement, selectedX: Int = 0, selectedY: Int = 0) {

  private val interactableIndices = element.interactableIndices()

  def content(): String = {
    element.content(highlightedIndex)
  }

  private def highlightedIndex: Int = {
    Try(interactableIndices(selectedY)(selectedX)).getOrElse(-1)
  }

  def navigateRight: ComposedConsoleFormatter = {
    if selectedX + 1 < interactableIndices(selectedY).length then {
      copy(selectedX = selectedX + 1)
    }
    else if selectedY + 1 < interactableIndices.length then copy(selectedX = 0, selectedY = selectedY + 1)
    else copy()
  }

  def navigateDown: ComposedConsoleFormatter = {
    if selectedY + 1 < interactableIndices.length then {
      if selectedX < interactableIndices(selectedY + 1).length then copy(selectedY = selectedY + 1)
      else copy(selectedY = selectedY + 1, selectedX = interactableIndices(selectedY + 1).length - 1)
    }
    else copy()
  }

  def navigateLeft: ComposedConsoleFormatter = {
    if selectedX > 0 then copy(selectedX = selectedX - 1)
    else {
      if selectedY > 0 then copy(selectedY = selectedY - 1, selectedX = interactableIndices(selectedY - 1).length - 1)
      else copy()
    }
  }

  def navigateUp: ComposedConsoleFormatter = {
    if selectedY > 0 then {
      if selectedX < interactableIndices(selectedY - 1).length then copy(selectedY = selectedY - 1)
      else copy(selectedY = selectedY - 1, selectedX = interactableIndices(selectedY - 1).length - 1)
    }
    else copy()
  }
}