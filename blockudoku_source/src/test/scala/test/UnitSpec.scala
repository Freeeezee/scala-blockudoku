package test

import blockudoku.views.console.ConsoleView
import org.scalatest.matchers.should
import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.propspec.AnyPropSpec


class UnitSpec extends AnyPropSpec with TableDrivenPropertyChecks with should.Matchers {
  def viewContent(view: ConsoleView): String = {
    view.consoleElement.content(-1)
  }
}