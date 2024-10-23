package blockudoku.windows

import blockudoku.RandomImpl
import blockudoku.controllers.{ElementController, GridController}
import blockudoku.views.console.composed.{ComposedConsoleFormatter, VerticalFrame}
import blockudoku.views.console.{ConsoleElementView, ConsoleGridView, ConsoleHeadlineView, ConsoleView}

class ConsoleWindow extends Window {
  private val views = initializeViews()

  private def initializeViews(): List[ConsoleView] = {
    var views: List[ConsoleView] = List()

    views = views :+ initializeHeadlineView()
    views = views :+ initializeGridView()
    views = views :+ initializeElementView()
    views
  }
  private def initializeHeadlineView(): ConsoleView = {
    val gridController = GridController()
    val width = gridController.grid.xLength * 5 + 1
    ConsoleHeadlineView(width)
  }
  private def initializeGridView(): ConsoleView = {
    val gridController = GridController()
    ConsoleGridView(gridController)
  }
  private def initializeElementView(): ConsoleView = {
    val gridController = GridController()
    val elementController = ElementController(new RandomImpl())
    ConsoleElementView(gridController, elementController)
  }

  override def display(): Unit = {
    clearConsole()

    println(formatter.content())
  }
  private def clearConsole(): Unit = {
    println("\u001b[2J")
  }
  private def formatter: ComposedConsoleFormatter = {
    // TODO: Not interactable at the moment and nothing is highlighted.
    val verticalFrame = VerticalFrame(views.map(_.consoleElement))(0, isInteractable = false)
    ComposedConsoleFormatter(verticalFrame, -1, -1)
  }
}
