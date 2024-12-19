package test.views

import blockudoku.views.console.{ConsoleElementView, ConsoleGridView, ConsoleHeadlineView}
import blockudoku.windows.Window
import io.gitlab.freeeezee.yadis.Lifetime.Singleton
import test.UnitSpec

class ViewSpec extends UnitSpec {
  
  override def beforeEach(): Unit = {
    super.beforeEach()
    container.register[ConsoleElementView](Singleton)
    container.register[ConsoleGridView](Singleton)
    container.register[Window, WindowMock](Singleton)
    provider = container.buildProvider()
  }
}
