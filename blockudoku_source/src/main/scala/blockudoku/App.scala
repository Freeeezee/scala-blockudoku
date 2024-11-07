package blockudoku

import blockudoku.input.ConsoleInputHandler
import blockudoku.windows.ConsoleWindow

object App {
  private var exitRequested = false

  def run(): Unit = {
    val window = new ConsoleWindow()

    val inputHandler = new ConsoleInputHandler(window)

    while (!exitRequested) {
      if (window.changed) {
        window.display()
      }

      inputHandler.run()
    }

    inputHandler.close()
  }

  def exit(): Unit = {
    exitRequested = true
  }
}
