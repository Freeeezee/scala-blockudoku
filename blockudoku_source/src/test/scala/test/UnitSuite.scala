package test

import blockudoku.views.console.ConsoleView
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

abstract class UnitSuite extends AnyFunSuite with Matchers {
  def viewContent(view: ConsoleView): String = {
    view.consoleElement.content(-1)
  }
}
