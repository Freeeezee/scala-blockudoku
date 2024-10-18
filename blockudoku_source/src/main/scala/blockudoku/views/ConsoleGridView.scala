package blockudoku.views

import blockudoku.controllers.GridController
import blockudoku.{ObservableObject, Observer}
import blockudoku.models.Grid
import blockudoku.services.console.GridFormatter

case class ConsoleGridView(gridController: GridController) extends View, ObservableObject[ConsoleGridView], Observer[Grid] {
  override def display(): Unit = {
    print(GridFormatter(gridController.grid).formatted)
  }

  override def receiveUpdate(): Unit = notifyObservers()
}
