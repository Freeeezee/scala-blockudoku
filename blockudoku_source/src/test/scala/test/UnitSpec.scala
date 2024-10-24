package test

import blockudoku.views.console.ConsoleView
import org.scalatest.GivenWhenThen
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers
abstract class UnitSpec extends AnyFunSpec with Matchers {
  def viewContent(view: ConsoleView): String = {
    view.consoleElement.content(-1)
  }
}