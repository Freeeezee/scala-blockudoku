package blockudoku.views.console.builders

import blockudoku.controllers.{ControllerMediator, ElementController, GridController}
import blockudoku.views.console.{ConsoleElementView, ConsoleGridView, ConsoleHeadlineView, ConsoleView}
import blockudoku.windows.FocusManager

class GameViewBuilder(mediator: ControllerMediator, gridController: GridController,
                      elementController: ElementController, focusManager: FocusManager) extends ViewBuilder {
  
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
    ConsoleGridView(mediator, gridController, elementController, focusManager)
  }

  private def initializeElementView(): ConsoleView = {
    ConsoleElementView(mediator, gridController, elementController, focusManager)
  }

  override def build(): List[ConsoleView] = {
    initializeViews()
  }
}
