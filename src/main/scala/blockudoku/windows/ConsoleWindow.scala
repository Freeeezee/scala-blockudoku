package blockudoku.windows

import blockudoku.App
import blockudoku.commands.{CommandFactory, CommandInvoker}
import blockudoku.controllers.mediatorImpl.{ElementController, GridController}
import blockudoku.controllers.{ControllerMediator, ElementCollector, GridCollector}
import blockudoku.services.{ApplicationThread, CancelableTask, GridPreviewBuilder}
import blockudoku.views.console.composed.Direction.{Down, Left, Right, Up}
import blockudoku.views.console.composed.{ComposedConsoleFormatter, Direction, VerticalFrame}
import blockudoku.views.console.input.ConsoleInputHandler
import blockudoku.views.console.{ConsoleElementView, ConsoleGridView, ConsoleHeadlineView, ConsoleView}

class ConsoleWindow(commandFactory: CommandFactory, commandInvoker: CommandInvoker, gridCollector: GridCollector, elementCollector: ElementCollector, focusManager: FocusManager, handler: ConsoleInputHandler, previewBuilder: GridPreviewBuilder) extends Window {

  initializeEvents()
  
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
    val width = gridCollector.getGrid.xLength * 5 + 1
    ConsoleHeadlineView(width, focusManager, this)
  }
  private def initializeGridView(): ConsoleView = {
    ConsoleGridView(commandFactory, commandInvoker, gridCollector, elementCollector, focusManager, this, previewBuilder)
  }
  private def initializeElementView(): ConsoleView = {
    ConsoleElementView(commandFactory, commandInvoker, elementCollector, gridCollector, focusManager, this)
  }

  override def display(): Unit = {

    while(true) {
      printContent()
      inputTask = Some(ApplicationThread().run {
        handleInput()
      })
      inputTask match {
        case Some(task) => task.await()
        case None => ()
      }
    }
  }

  private def printContent(): Unit = {
    clearConsole()
    println(content)
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
  }

  def select(): Unit = {
    formatter.select()
  }
  
  private def handleInput(): Unit = {
    handler.run()
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

  private def initializeEvents(): Unit = {
    
    handler.arrowUpKey.addListener(() => navigate(Up))
    handler.arrowDownKey.addListener(() => navigate(Down))
    handler.arrowLeftKey.addListener(() => navigate(Left))
    handler.arrowRightKey.addListener(() => navigate(Right))

    handler.zKey.addListener(() => undo())
    handler.rKey.addListener(() => redo())

    handler.enterKey.addListener(() => select())

    handler.qKey.addListener(() => App.exit())
  }
}
