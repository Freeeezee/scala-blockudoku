package test.views

import blockudoku.controllers.ControllerMediator
import blockudoku.controllers.mediatorImpl.{ElementController, GridController}
import blockudoku.views.console.ConsoleGridView
import blockudoku.views.console.composed.ComposedConsoleFormatter
import blockudoku.windows.FocusManager
import blockudoku.windows.FocusState.Grid
import test.GridProvider

class ConsoleGridViewSpec extends ViewSpec {
  
  "GridView" when {
    "size 9x9" should {
      "display a 9x9 grid" in {
        use(GridProvider.emptyGrid(9))
        val gridView = provider.get[ConsoleGridView]
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
        use(GridProvider.emptyGrid(4))
        val gridView = provider.get[ConsoleGridView]
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
        use(GridProvider.emptyGrid(6))
        val gridView = provider.get[ConsoleGridView]
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
        use(GridProvider.emptyGrid(6))

        val gridView = provider.get[ConsoleGridView]
        val elementController = provider.get[ElementController]
        val mediator = provider.get[ControllerMediator]

        mediator.setElement(elementController.elements.head, 0)

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
        val gridView = provider.get[ConsoleGridView]
        val gridController = provider.get[GridController]
        val focusManager = provider.get[FocusManager]
        val elementController = provider.get[ElementController]

        elementController.selectElement(elementController.elements.head)
        focusManager.setFocusState(Grid)
        
        val formatter = ComposedConsoleFormatter.create(gridView.consoleElement)

        formatter.select()

        gridController.grid.tiles.head.state should be(blockudoku.models.TileState.blocked)
      }
    }

    "previewing" should {
      "display a green preview when the element can be placed" in {
        use(GridProvider.emptyGrid(6))

        val gridView = provider.get[ConsoleGridView]
        val gridController = provider.get[GridController]
        val elementController = provider.get[ElementController]

        elementController.selectElement(elementController.elements.head)

        gridView.onTileHighlighted(gridController.grid.tile(0, 0).get)

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
        use(GridProvider.emptyGrid(6))
        val gridView = provider.get[ConsoleGridView]
        val gridController = provider.get[GridController]
        val mediator = provider.get[ControllerMediator]
        val elementController = provider.get[ElementController]

        mediator.setElement(elementController.elements.head, 0)

        elementController.selectElement(elementController.elements.head)

        gridView.onTileHighlighted(gridController.grid.tile(0, 0).get)

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
        use(GridProvider.emptyGrid(6))

        val gridView = provider.get[ConsoleGridView]
        val gridController = provider.get[GridController]
        

        gridView.onTileHighlighted(gridController.grid.tile(5, 0).get)

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
