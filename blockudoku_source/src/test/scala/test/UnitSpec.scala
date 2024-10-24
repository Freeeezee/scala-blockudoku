package test

import blockudoku.views.console.ConsoleView
import org.scalatest.refspec.RefSpec

class UnitSpec extends RefSpec with org.scalatest.matchers.should.Matchers {
  def viewContent(view: ConsoleView): String = {
    view.consoleElement.content(-1)
  }
}
