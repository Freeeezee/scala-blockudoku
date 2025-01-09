package blockudoku.views.console

import blockudoku.commands.commandsImpl.SelectElementCommand
import blockudoku.commands.{CommandFactory, CommandInvoker}
import blockudoku.controllers.{ElementCollector, GridCollector}
import blockudoku.models.Element
import blockudoku.observer.Observer
import blockudoku.services.console.ElementFormatter
import blockudoku.views.console.composed.{ConsoleElement, HorizontalFrame, RegularConsoleElement, VerticalFrame}
import blockudoku.windows.{FocusManager, FocusState, Window}


case class ConsoleElementView (window: Window) (using commandFactory: CommandFactory, commandInvoker: CommandInvoker, elementCollector: ElementCollector, gridCollector: GridCollector,
                              focusManager: FocusManager) extends ConsoleView(focusManager, window), Observer {
  override val interactableFocusStates: Set[FocusState] = Set(FocusState.Elements)

  elementCollector.addObserver(this)
  
  private val headline = "Elements_"
  private val spacing = 15

  override def consoleElement: ConsoleElement = {
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
    val width = gridCollector.getGrid.xLength * 5 + 1

    if (headline.length > width) 0
    else (width - headline.length - 2) / 2
  }

  private def formattedElements: ConsoleElement = {
    val list = elementCollector.getElements
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
    setUpdated()
  }
}
