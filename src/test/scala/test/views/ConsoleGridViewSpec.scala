package test.views
import blockudoku.controllers.{ElementController, GridController}
import blockudoku.views.console.ConsoleGridView
import blockudoku.views.console.composed.ComposedConsoleFormatter
import blockudoku.windows.FocusManager
import blockudoku.windows.FocusState.Grid
import test.{RandomMock, UnitSpec}
// replace("\r\n", "\n") is used to make the tests pass on Windows
class ConsoleGridViewSpec extends UnitSpec {
  "GridView" when {
    "size 9x9" should {
      "display a 9x9 grid" in {
        val newGridController = GridController(9, 9, elementController, new FocusManager(Grid))
        val focusManager = new FocusManager(Grid)
        val gridView = ConsoleGridView(newGridController, ElementController(RandomMock(), focusManager), focusManager)
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
        val newGridController = GridController(4, 4, elementController, new FocusManager(Grid))
        val focusManager = new FocusManager(Grid)
        val gridView = ConsoleGridView(newGridController, ElementController(RandomMock(), focusManager), focusManager)
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
        val newGridController = GridController(6, 6, elementController, new FocusManager(Grid))
        val focusManager = new FocusManager(Grid)
        val gridView = ConsoleGridView(newGridController, ElementController(RandomMock(), focusManager), focusManager)
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

    "an element is added" should {
      "display the element at the correct position" in {
        val newElementController = ElementController(RandomMock(), new FocusManager(Grid))
        val newGridController = GridController(6, 6, newElementController, new FocusManager(Grid))
        val focusManager = new FocusManager(Grid)
        val elementController = ElementController(RandomMock(), focusManager)
        val gridView = ConsoleGridView(newGridController, newElementController, focusManager)
        newGridController.setElement(newElementController.elements(0), 0)

        viewContent(gridView).replace("\r\n", "\n") should be(
          """x----x----x----x----x----x----x
            || xx | xx |    |    |    |    |
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
    
    "formatted" should {
      "select the correct tile" in {
        val focusManager = new FocusManager(Grid)
        val elementController = ElementController(RandomMock(), focusManager)
        elementController.selectElement(elementController.elements(0))
        val gridView = ConsoleGridView(gridController, elementController, focusManager)
        val formatter = ComposedConsoleFormatter.create(gridView.consoleElement)

        formatter.select()

        gridController.grid.tiles.head.state should be(blockudoku.models.TileState.blocked)
      }
    }

    "previewing" should {
      "display a green preview when the element can be placed" in {
        val focusManager = new FocusManager(Grid)
        val elementController = ElementController(RandomMock(), focusManager)
        elementController.selectElement(elementController.elements(0))
        val newGridController = GridController(6, 6, elementController, focusManager)
        val gridView = ConsoleGridView(newGridController, elementController, focusManager)

        gridView.onTileHighlighted(newGridController.grid.tile(0, 0).get)

        viewContent(gridView).replace("\r\n", "\n") should be(
          """x----x----x----x----x----x----x
            || [32mxx[0m | [32mxx[0m |    |    |    |    |
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
      "display a red preview when the element is blocked by another" in {
        val focusManager = new FocusManager(Grid)
        val elementController = ElementController(RandomMock(), focusManager)
        elementController.selectElement(elementController.elements(0))
        val newGridController = GridController(6, 6, elementController, focusManager)
        val gridView = ConsoleGridView(newGridController, elementController, focusManager)

        newGridController.setElement(elementController.elements(0), 0)

        gridView.onTileHighlighted(newGridController.grid.tile(0, 0).get)

        viewContent(gridView).replace("\r\n", "\n") should be(
          """x----x----x----x----x----x----x
            || [31mxx[0m | [31mxx[0m |    |    |    |    |
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

      "not display anything when the element is out of bounds" in {
        val focusManager = new FocusManager(Grid)
        val elementController = ElementController(RandomMock(), focusManager)
        elementController.selectElement(elementController.elements(0))
        val newGridController = GridController(6, 6, elementController, focusManager)
        val gridView = ConsoleGridView(newGridController, elementController, focusManager)

        gridView.onTileHighlighted(newGridController.grid.tile(5, 0).get)

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
