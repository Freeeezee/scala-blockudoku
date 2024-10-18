package blockudoku

import blockudoku.windows.{ConsoleWindow, Window}
import scala.io.StdIn
import blockudoku.controllers.GridController
import blockudoku.controllers.ElementController

@main def main(): Unit =
  val gridController = GridController()
  val elementController = ElementController()
  val window: Window = ConsoleWindow(gridController, elementController)
  window.display()
  println("Tom war hier")
  println("Milena war hier")
  println("Select Element:")
  // 
  println("Select Position:")
  var selectedPosition: Int = StdIn.readInt() //Position / tile
  val element = elementController.elements(1)
  gridController.setElement(element, selectedPosition)
  window.display()