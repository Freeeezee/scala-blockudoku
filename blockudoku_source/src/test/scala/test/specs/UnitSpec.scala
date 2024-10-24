package test.specs

import blockudoku.views.console.ConsoleView
import org.specs2.matcher.Matchers
import org.specs2.mutable.Specification

class UnitSpec extends Specification with Matchers {
  def viewContent(view: ConsoleView): String = {
    view.consoleElement.content(-1)
  }
}
