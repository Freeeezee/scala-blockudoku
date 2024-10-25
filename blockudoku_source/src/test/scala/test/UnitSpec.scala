package test

import blockudoku.views.console.ConsoleView
import org.scalatest.GivenWhenThen
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class UnitSpec extends AnyFlatSpec with Matchers with GivenWhenThen {
  def viewContent(view: ConsoleView): String = {
    view.consoleElement.content(-1)
  }
}

