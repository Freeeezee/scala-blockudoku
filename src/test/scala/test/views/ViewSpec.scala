package test.views

import blockudoku.views.console.{ConsoleElementView, ConsoleGridView, ConsoleView}
import blockudoku.windows.Window
import io.gitlab.freeeezee.yadis.Lifetime.Singleton
import test.UnitSpec

class ViewSpec extends UnitSpec {

  override def onConfigureContainers(): Unit = {
    includeDefaultConfig()
    includeCommands()
    includeControllers()

    container.register[ConsoleElementView](Singleton)
    container.register[ConsoleGridView](Singleton)
    container.register[Window, WindowMock](Singleton)
  }

  def viewContent(view: ConsoleView): String = {
    view.consoleElement.content(-1)
  }
}
