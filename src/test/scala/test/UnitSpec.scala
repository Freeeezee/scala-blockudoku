package test

import blockudoku.controllers.{ElementController, GridController}
import blockudoku.views.console.ConsoleView
import blockudoku.windows.FocusManager
import blockudoku.windows.FocusState.Elements
import io.gitlab.freeeezee.yadis.ComponentContainer
import org.scalatest.{BeforeAndAfterEach, GivenWhenThen}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import scala.compiletime.uninitialized

abstract class UnitSpec extends AnyWordSpec with Matchers with GivenWhenThen with BeforeAndAfterEach {
  
  var container : ComponentContainer = uninitialized

  override def beforeEach(): Unit = {
    super.beforeEach()

  }

  def viewContent(view: ConsoleView): String = {
    view.consoleElement.content(-1)
  }
}
