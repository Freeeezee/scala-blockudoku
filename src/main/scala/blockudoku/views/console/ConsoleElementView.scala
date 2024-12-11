package blockudoku.views.console

import blockudoku.commands.{CommandFactory, CommandInvoker, SelectElementCommand}
import blockudoku.controllers.{ControllerMediator, ElementController, GridController}
import blockudoku.models.Element
import blockudoku.observer.Observer
import blockudoku.services.console.ElementFormatter
import blockudoku.views.console.composed.{ConsoleElement, HorizontalFrame, RegularConsoleElement, VerticalFrame}
import blockudoku.windows.{FocusManager, FocusState}

case class ConsoleElementView(commandFactory: CommandFactory, commandInvoker: CommandInvoker, gridController: GridController, elementController: ElementController,
                              focusManager: FocusManager) extends ConsoleView(focusManager), Observer {
  override val interactableFocusStates: Set[FocusState] = Set(FocusState.Elements)

  elementController.addObserver(this)
  
  private val headline = "Elements_"
  private val spacing = 15

  override def consoleElement: ConsoleElement = {
    changed = false
    formatted
  }

  
  private def formatted: ConsoleElement = {
    val list = List[ConsoleElement](
      formattedHeadline,
      formattedElements,
    )

    VerticalFrame(list)(1, isInteractable = focused)
  }

  private def formattedHeadline: ConsoleElement = {
    RegularConsoleElement(s"$headlineDividerLine $headline $headlineDividerLine")
  }
  private def headlineDividerLine: String = {
    "-" * headlineDividerLineLength
  }
  private def headlineDividerLineLength: Int = {
    val width = gridController.grid.value.xLength * 5 + 1

    if (headline.length > width) 0
    else (width - headline.length - 2) / 2
  }

  private def formattedElements: ConsoleElement = {
    val list = elementController.elements.value
      .map(ElementFormatter.apply)
      .map(formatter => RegularConsoleElement(formatter.content, isInteractable = true, 
        onSelect = () => selectElementCommand(formatter.element)))

    HorizontalFrame(list)(spacing, isInteractable = true)
  }
  private def selectElementCommand(element: Element): Unit = {
    val command = commandFactory.createSelectElementCommand(element)
    commandInvoker.execute(command)
  }

  override def update(): Unit = {
    changed = true
  }
}
