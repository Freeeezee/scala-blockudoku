package blockudoku.views.console.composed

import blockudoku.services.MathExt

import scala.util.Try

case class ComposedConsoleFormatter private (element: ConsoleElement, interactableIndices: List[List[Int]],
                                             selectedX: Int = 0, selectedY: Int = 0) {
  def content(): String = {
    element.content(highlightedIndex)
  }

  private def highlightedIndex: Int = {
    Try(interactableIndices(selectedY)(selectedX)).getOrElse(-1)
  }

  def navigate(direction: Direction): ComposedConsoleFormatter = {
    direction match {
      case Direction.Up => navigateUp
      case Direction.Down => navigateDown
      case Direction.Left => navigateLeft
      case Direction.Right => navigateRight
    }
  }
  
  private def navigateRight: ComposedConsoleFormatter = {
    if selectedX + 1 < interactableIndices(selectedY).length then {
      copy(selectedX = selectedX + 1)
    }
    else if selectedY + 1 < interactableIndices.length then copy(selectedX = 0, selectedY = selectedY + 1)
    else copy()
  }

  private def navigateDown: ComposedConsoleFormatter = {
    if selectedY + 1 < interactableIndices.length then {
      if selectedX < interactableIndices(selectedY + 1).length then copy(selectedY = selectedY + 1)
      else copy(selectedY = selectedY + 1, selectedX = interactableIndices(selectedY + 1).length - 1)
    }
    else copy()
  }

  private def navigateLeft: ComposedConsoleFormatter = {
    if selectedX > 0 then copy(selectedX = selectedX - 1)
    else {
      if selectedY > 0 then copy(selectedY = selectedY - 1, selectedX = interactableIndices(selectedY - 1).length - 1)
      else copy()
    }
  }

  private def navigateUp: ComposedConsoleFormatter = {
    if selectedY > 0 then {
      if selectedX < interactableIndices(selectedY - 1).length then copy(selectedY = selectedY - 1)
      else copy(selectedY = selectedY - 1, selectedX = interactableIndices(selectedY - 1).length - 1)
    }
    else copy()
  }

  def select(): Unit = {
    element.get(highlightedIndex).onSelect()
  }
}

object ComposedConsoleFormatter {
  def create(element: ConsoleElement, selectedX: Int = 0, selectedY: Int = 0): ComposedConsoleFormatter = {
    val interactableIndices = element.interactableIndices()

    val clampedY = MathExt.clamp(selectedY, 0, interactableIndices.length - 1)
    val clampedX = MathExt.clamp(selectedX, 0, interactableIndices(clampedY).length - 1)

    if selectedY == -1 || selectedX == -1 then ComposedConsoleFormatter(element, interactableIndices, selectedX, selectedY)
    else ComposedConsoleFormatter(element, interactableIndices, clampedX, clampedY)
  }
}