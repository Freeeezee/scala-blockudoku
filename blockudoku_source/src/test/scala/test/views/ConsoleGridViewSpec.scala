package test.views
import test.UnitSpec
import blockudoku.controllers.GridController
import blockudoku.views.console.ConsoleGridView
import blockudoku.windows.FocusManager
import blockudoku.windows.FocusState.{Elements, Grid}
// replace("\r\n", "\n") is used to make the tests pass on Windows
class ConsoleGridViewSpec extends UnitSpec {
  "GridView" when {
    "size 9x9" should {
      "display a 9x9 grid" in {
        val gridController = GridController(9, 9)
        val gridView = ConsoleGridView(gridController, new FocusManager(Grid))
        viewContent(gridView).replace("\r\n", "\n") should be(
          """x----x----x----x----x----x----x----x----x----x
            ||    |    |    |    |    |    |    |    |    |
            |x----x----x----x----x----x----x----x----x----x
            ||    |    |    |    |    |    |    |    |    |
            |x----x----x----x----x----x----x----x----x----x
            ||    |    |    |    |    |    |    |    |    |
            |x----x----x----x----x----x----x----x----x----x
            ||    |    |    |    |    |    |    |    |    |
            |x----x----x----x----x----x----x----x----x----x
            ||    |    |    |    |    |    |    |    |    |
            |x----x----x----x----x----x----x----x----x----x
            ||    |    |    |    |    |    |    |    |    |
            |x----x----x----x----x----x----x----x----x----x
            ||    |    |    |    |    |    |    |    |    |
            |x----x----x----x----x----x----x----x----x----x
            ||    |    |    |    |    |    |    |    |    |
            |x----x----x----x----x----x----x----x----x----x
            ||    |    |    |    |    |    |    |    |    |
            |x----x----x----x----x----x----x----x----x----x
            |""".stripMargin.replace("\r\n", "\n"))
      }
    }
    "size 4x4" should {
      "display a 4x4 grid" in {
        val gridController = GridController(4, 4)
        val gridView = ConsoleGridView(gridController, new FocusManager(Grid))
        viewContent(gridView).replace("\r\n", "\n") should be(
          """x----x----x----x----x
            ||    |    |    |    |
            |x----x----x----x----x
            ||    |    |    |    |
            |x----x----x----x----x
            ||    |    |    |    |
            |x----x----x----x----x
            ||    |    |    |    |
            |x----x----x----x----x
            |""".stripMargin.replace("\r\n", "\n"))
      }
    }
    "size 6x6" should {
      "display a 6x6 grid" in {
        val gridController = GridController(6, 6)
        val gridView = ConsoleGridView(gridController, new FocusManager(Grid))
        viewContent(gridView).replace("\r\n", "\n") should be(
          """x----x----x----x----x----x----x
            ||    |    |    |    |    |    |
            |x----x----x----x----x----x----x
            ||    |    |    |    |    |    |
            |x----x----x----x----x----x----x
            ||    |    |    |    |    |    |
            |x----x----x----x----x----x----x
            ||    |    |    |    |    |    |
            |x----x----x----x----x----x----x
            ||    |    |    |    |    |    |
            |x----x----x----x----x----x----x
            ||    |    |    |    |    |    |
            |x----x----x----x----x----x----x
            |""".stripMargin.replace("\r\n", "\n"))
      }
    }
  }
}
