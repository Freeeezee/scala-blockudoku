package blockudoku

import blockudoku.windows.{ConsoleWindow, Window}

import sys.process._


@main def main(): Unit =
  val window: Window = ConsoleWindow()
  window.display()