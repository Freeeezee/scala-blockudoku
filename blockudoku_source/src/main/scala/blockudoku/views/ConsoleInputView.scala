package blockudoku.views

import blockudoku.{ObservableObject, Observer}
import blockudoku.services.console.ConsoleInputHandler

import scala.io.StdIn.readLine

case class ConsoleInputView() extends View, ObservableObject[ConsoleInputView], Observer[ConsoleInputHandler] {
  private var inputHandler = ConsoleInputHandler()

  override def display(): Unit = {
    displayInputState()
    inputHandler = inputHandler.input(readInput())
  }
  private def displayInputState(): Unit = {
    if inputHandler.elementState then displayElementState()
    else displayGridState()
  }
  private def displayElementState(): Unit = {
    println("Enter an element number...")
  }
  private def displayGridState(): Unit = {
    println("Enter a grid number...")
  }

  private def readInput(): String = {
    readLine()
  }

  override def receiveUpdate(): Unit = notifyObservers()
}
