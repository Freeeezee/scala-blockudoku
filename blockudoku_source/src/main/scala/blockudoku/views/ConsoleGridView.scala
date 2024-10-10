package blockudoku.views

import blockudoku.{ObservableObject, Observer}
import blockudoku.models.Grid
import blockudoku.services.console.GridFormatter

case class ConsoleGridView(grid: Grid) extends View, ObservableObject[ConsoleGridView], Observer[Grid] {
  override def display(): Unit = {
    print(GridFormatter(grid).formatted)
  }

  override def receiveUpdate(): Unit = notifyObservers()
}
