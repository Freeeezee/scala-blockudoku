package test

import blockudoku.views.console.ConsoleView

abstract class UnitSpec {
  def viewContent(view: ConsoleView): String = {
    view.consoleElement.content(-1)
  }
}
