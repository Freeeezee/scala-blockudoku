package test

import blockudoku.views.console.ConsoleView
import org.scalatest.GivenWhenThen
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

abstract class UnitSpec extends AnyWordSpec with Matchers with GivenWhenThen {
  def viewContent(view: ConsoleView): String = {
    view.consoleElement.content(-1)
  }
}
