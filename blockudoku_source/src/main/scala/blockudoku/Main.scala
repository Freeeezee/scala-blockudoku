package blockudoku

import blockudoku.windows.{ConsoleWindow, Window}

@main def main(): Unit =
  val window: Window = ConsoleWindow()
  window.display()
  println("Tom war hier")
  println("Milena war hier")