package blockudoku

import blockudoku.windows.{ConsoleWindow, TestWindow, Window}

import sys.process.*


@main def main(): Unit =
  val window: Window = TestWindow()
  window.display()