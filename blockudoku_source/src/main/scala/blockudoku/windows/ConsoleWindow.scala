package blockudoku.windows

import blockudoku.Observer
import blockudoku.controllers.{ElementController, GridController}
import blockudoku.views.{ConsoleElementView, ConsoleGridView, ConsoleHeadlineView, View}

class ConsoleWindow(gridController: GridController, elementController: ElementController) extends Window, Observer[ConsoleGridView] {
  private val views = initializeViews(gridController, elementController)

  private def initializeViews(gridController: GridController, elementController: ElementController): List[View] = {
    var views: List[View] = List()

    views = views :+ initializeHeadlineView()
    views = views :+ initializeGridView(gridController)
    views = views :+ initializeElementView(gridController, elementController)
    views
  }
  private def initializeHeadlineView(): View = {
    val gridController = GridController()
    val width = gridController.grid.xLength * 5 + 1
    ConsoleHeadlineView(width)
  }
  private def initializeGridView(gridController: GridController): View = {

    //val gridController = GridController()
    ConsoleGridView(gridController)
  }
  private def initializeElementView(gridController: GridController, elementController: ElementController): View = {
    //val gridController = GridController()
    val width = gridController.grid.xLength * 5 + 1
    //val elementController = ElementController()
    ConsoleElementView(elementController.maxElementLength, elementController.elements, width)
  }
  override def display(): Unit = {
    println("\u001b[2J") // Clear console

    views.foreach(_.display())
  }
  override def receiveUpdate(): Unit = display() 
}
