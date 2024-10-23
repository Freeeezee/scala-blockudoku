package blockudoku

import blockudoku.windows.{ConsoleWindow, Window}

@main def main(): Unit =
  val window: Window = ConsoleWindow()
  window.display()