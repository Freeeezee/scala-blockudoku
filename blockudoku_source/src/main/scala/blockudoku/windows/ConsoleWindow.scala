package blockudoku.windows

import blockudoku.Observer
import blockudoku.controllers.{ElementController, GridController}
import blockudoku.views.{ConsoleElementView, ConsoleGridView, ConsoleHeadlineView, ConsoleInputView, View}

class ConsoleWindow extends Window, Observer[ConsoleGridView] {
  private val views = initializeViews()

  private def initializeViews(): List[View] = {
    var views: List[View] = List()

    views = views :+ initializeHeadlineView()
    views = views :+ initializeGridView()
    views = views :+ initializeElementView()
    views = views :+ initializeInputView()
    views
  }
  private def initializeHeadlineView(): View = {
    val gridController = GridController()
    val width = gridController.grid.xLength * 5 + 1
    ConsoleHeadlineView(width)
  }
  private def initializeGridView(): View = {
    val gridController = GridController()
    ConsoleGridView(gridController.grid)
  }
  private def initializeElementView(): View = {
    val gridController = GridController()
    val width = gridController.grid.xLength * 5 + 1
    val elementController = ElementController()
    ConsoleElementView(elementController.maxElementLength, elementController.elements, width)
  }
  private def initializeInputView(): View = {
    ConsoleInputView()
  }
  override def display(): Unit = {
    println("\u001b[2J") // Clear console

    views.foreach(_.display())
  }
  override def receiveUpdate(): Unit = display() 
}
