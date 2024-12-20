package test

import blockudoku.controllers.mediatorImpl.{ElementController, GridController}
import blockudoku.views.console.ConsoleView
import blockudoku.windows.FocusManager
import blockudoku.windows.focusManagerImpl.FocusState.Elements
import io.gitlab.freeeezee.yadis.{ComponentContainer, ComponentProvider}
import org.scalatest.{BeforeAndAfterEach, GivenWhenThen}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import blockudoku.registerControllers
import blockudoku.services.Random
import io.gitlab.freeeezee.yadis.Lifetime.Singleton

import scala.compiletime.uninitialized

abstract class UnitSpec extends AnyWordSpec with Matchers with GivenWhenThen with BeforeAndAfterEach {
  
  var container : ComponentContainer = uninitialized
  var provider : ComponentProvider = uninitialized

  private def registerRandom(componentContainer: ComponentContainer): Unit = {
    componentContainer.register[Random, RandomMock](Singleton)
  }
  
  override def beforeEach(): Unit = {
    super.beforeEach()
    container = ComponentContainer()
    registerRandom(container)
    provider = container.registerControllers().buildProvider()
  }

  def viewContent(view: ConsoleView): String = {
    view.consoleElement.content(-1)
  }
}
