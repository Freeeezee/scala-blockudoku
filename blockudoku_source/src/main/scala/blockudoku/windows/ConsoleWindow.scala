package blockudoku.windows

import blockudoku.RandomImpl
import blockudoku.controllers.{ElementController, GridController}
import blockudoku.views.console.composed.{ComposedConsoleFormatter, Direction, VerticalFrame}
import blockudoku.views.console.{ConsoleElementView, ConsoleGridView, ConsoleHeadlineView, ConsoleView}

class ConsoleWindow extends Window {
  private val views = initializeViews()

  var changed: Boolean = true
  private var formatter = initializedFormatter

  private def initializeViews(): List[ConsoleView] = {
    var views: List[ConsoleView] = List()

    views = views :+ initializeHeadlineView()
    views = views :+ initializeGridView()
    views = views :+ initializeElementView()
    views
  }
  private def initializeHeadlineView(): ConsoleView = {
    val gridController = GridController(9, 9)
    val width = gridController.grid.xLength * 5 + 1
    ConsoleHeadlineView(width)
  }
  private def initializeGridView(): ConsoleView = {
    val gridController = GridController(9, 9)
    ConsoleGridView(gridController)
  }
  private def initializeElementView(): ConsoleView = {
    val gridController = GridController(9, 9)
    val elementController = ElementController(new RandomImpl())
    ConsoleElementView(gridController, elementController)
  }

  override def display(): Unit = {
    clearConsole()

    println(content)
    
    changed = false
  }
  
  def content: String = formatter.content()
  
  private def clearConsole(): Unit = {
    println("\u001b[2J")
  }

  private def initializedFormatter: ComposedConsoleFormatter = {
    val verticalFrame = VerticalFrame(views.map(_.consoleElement))(0, isInteractable = true)
    ComposedConsoleFormatter(verticalFrame)
  }

  def navigate(direction: Direction): Unit = {
    formatter = formatter.navigate(direction)
    changed = true
  }
}
