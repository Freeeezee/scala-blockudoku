package test

import blockudoku.views.console.ConsoleView
import org.scalatest.GivenWhenThen
import org.scalatest.featurespec.AnyFeatureSpec
import org.scalatest.matchers.should.Matchers

class UnitSpec extends AnyFeatureSpec with GivenWhenThen with Matchers {
  def viewContent(view: ConsoleView): String = {
    view.consoleElement.content(-1)
  }
}
