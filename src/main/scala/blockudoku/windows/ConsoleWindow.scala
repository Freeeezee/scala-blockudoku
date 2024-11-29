package blockudoku.windows

import blockudoku.commands.{CommandFactory, CommandInvoker}
import blockudoku.controllers.{ControllerMediator, ElementController, GridController}
import blockudoku.input.ConsoleInputHandler
import blockudoku.views.console.composed.{ComposedConsoleFormatter, Direction, VerticalFrame}
import blockudoku.views.console.{ConsoleElementView, ConsoleGridView, ConsoleHeadlineView, ConsoleView}

class ConsoleWindow(commandFactory: CommandFactory, commandInvoker: CommandInvoker, gridController: GridController, elementController: ElementController, focusManager: FocusManager, inputHandler: ConsoleInputHandler) extends Window {
  private val views = initializeViews()

  var changed: Boolean = true
  private var formatter = createFormatter(0, 0)

  private def initializeViews(): List[ConsoleView] = {
    var views: List[ConsoleView] = List()

    views = views :+ initializeHeadlineView()
    views = views :+ initializeGridView()
    views = views :+ initializeElementView()
    views
  }
  private def initializeHeadlineView(): ConsoleView = {
    val width = gridController.grid.xLength * 5 + 1
    ConsoleHeadlineView(width, focusManager)
  }
  private def initializeGridView(): ConsoleView = {
    ConsoleGridView(commandFactory, commandInvoker, gridController, elementController, focusManager)
  }
  private def initializeElementView(): ConsoleView = {
    ConsoleElementView(commandFactory, commandInvoker, gridController, elementController, focusManager)
  }

  override def display(): Unit = {
    clearConsole()
    
    println(content)
    
  }
  
  def content: String = {
    formatter = createFormatter(formatter.selectedX, formatter.selectedY)
    formatter.content()
  }
  
  private def clearConsole(): Unit = {
    println("\u001b[2J")
  }

  private def createFormatter(selectedX: Int, selectedY: Int): ComposedConsoleFormatter = {
    val verticalFrame = VerticalFrame(views.map(_.consoleElement))(0, isInteractable = true)
    ComposedConsoleFormatter.create(verticalFrame, selectedX, selectedY)
  }

  def navigate(direction: Direction): Unit = {
    formatter = formatter.navigate(direction)
    changed = true
  }

  def select(): Unit = {
    formatter.select()
  }
  
  override def anyChange(): Boolean = {
    views.exists(_.changed) || changed
  }
  
  override def handleInput(): Unit = {
    inputHandler.run()
  }
}
