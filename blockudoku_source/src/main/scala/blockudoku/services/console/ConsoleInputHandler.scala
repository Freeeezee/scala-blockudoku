package blockudoku.services.console

import blockudoku.ObservableObject

import scala.util.{Failure, Success, Try}

case class ConsoleInputHandler(elementState: Boolean = false) extends ObservableObject[ConsoleInputHandler] {
  def input(input: String): ConsoleInputHandler = {
    val intInput = Try(input.toInt)

    intInput match
      case Success(value) => handleInput(value)
      case Failure(exception) => notifyObservers()

    this
  }

  private def handleInput(input: Int): Unit = {
    if elementState then handleInputElementState(input)
    else handleInputGridState(input)

    copy(!elementState)
  }

  private def handleInputElementState(input: Int): Unit = {

  }
  private def handleInputGridState(i: Int): Unit = {

  }
}