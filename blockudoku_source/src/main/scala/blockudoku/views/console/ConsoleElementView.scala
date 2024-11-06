package blockudoku.views.console

import blockudoku.controllers.{ElementController, GridController}
import blockudoku.models.Element
import blockudoku.services.console.ElementFormatter
import blockudoku.views.console.ConsoleView
import blockudoku.views.console.composed.{ConsoleElement, HorizontalFrame, RegularConsoleElement, VerticalFrame}

case class ConsoleElementView(gridController: GridController, elementController: ElementController) extends ConsoleView {
  private val headline = "Elements_"
  private val spacing = 15

  override def consoleElement: ConsoleElement = formatted

  private def formatted: ConsoleElement = {
    val list = List[ConsoleElement](
      formattedHeadline,
      formattedElements,
    )

    VerticalFrame(list)(1, isInteractable = true)
  }

  private def formattedHeadline: ConsoleElement = {
    RegularConsoleElement(s"$headlineDividerLine $headline $headlineDividerLine")
  }
  private def headlineDividerLine: String = {
    "-" * headlineDividerLineLength
  }
  private def headlineDividerLineLength: Int = {
    val width = gridController.grid.xLength * 5 + 1

    if (headline.length > width) 0
    else (width - headline.length - 2) / 2
  }

  private def formattedElements: ConsoleElement = {
    val list = elementController.elements
      .map(ElementFormatter.apply)
      .map(formatter => RegularConsoleElement(formatter.content, isInteractable = true))
      .toList

    HorizontalFrame(list)(spacing, isInteractable = true)
  }
}
