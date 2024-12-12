package blockudoku.windows

import blockudoku.commands.{CommandFactory, CommandInvoker}
import blockudoku.controllers.{ControllerMediator, ElementController, GridController}
import blockudoku.input.ConsoleInputHandler
import blockudoku.services.{ApplicationThread, CancelableTask}
import blockudoku.views.console.composed.{ComposedConsoleFormatter, Direction, VerticalFrame}
import blockudoku.views.console.{ConsoleElementView, ConsoleGridView, ConsoleHeadlineView, ConsoleView}

class ConsoleWindow(commandFactory: CommandFactory, commandInvoker: CommandInvoker, gridController: GridController, elementController: ElementController, focusManager: FocusManager, inputHandler: ConsoleInputHandler) extends Window {
  private val views = initializeViews()

  private var formatter = createFormatter(0, 0)

  private var inputTask : Option[CancelableTask] = None

  private def initializeViews(): List[ConsoleView] = {
    var views: List[ConsoleView] = List()

    views = views :+ initializeHeadlineView()
    views = views :+ initializeGridView()
    views = views :+ initializeElementView()
    views
  }
  private def initializeHeadlineView(): ConsoleView = {
    val width = gridController.grid.value.xLength * 5 + 1
    ConsoleHeadlineView(width, focusManager, this)
  }
  private def initializeGridView(): ConsoleView = {
    ConsoleGridView(commandFactory, commandInvoker, gridController, elementController, focusManager, this)
  }
  private def initializeElementView(): ConsoleView = {
    ConsoleElementView(commandFactory, commandInvoker, gridController, elementController, focusManager, this)
  }

  override def display(): Unit = {

    while(true) {
      println(content)
      inputTask = Some(ApplicationThread().run {
        handleInput()
      })
      inputTask match {
        case Some(task) => task.await()
        case None => ()
      }
    }
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
  }

  def select(): Unit = {
    formatter.select()
  }
  
  private def handleInput(): Unit = {
    inputHandler.run()
  }
  def undo(): Unit = {
    commandInvoker.undo()
  }
  def redo(): Unit = {
    commandInvoker.redo()
  }

  override def setUpdated(): Unit = {

    inputTask match {
      case Some(task) =>
        task.cancel()
        inputTask = None
      case None => ()
    }
  }
}
