package test.views
import blockudoku.controllers.{ControllerMediator, ElementController, ElementControllerImpl, GridConfig, GridController, GridControllerImpl}
import blockudoku.models.{ElementCollector, GridCollector}
import blockudoku.services.Random
import blockudoku.views.console.ConsoleGridView
import blockudoku.views.console.composed.ComposedConsoleFormatter
import blockudoku.windows.FocusManager
import blockudoku.windows.FocusState.Grid
import io.gitlab.freeeezee.yadis.{ComponentContainer, ComponentProvider}
import io.gitlab.freeeezee.yadis.Lifetime.Singleton
import test.{RandomMock, UnitSpec}
// replace("\r\n", "\n") is used to make the tests pass on Windows
class ConsoleGridViewSpec extends ViewSpec {
  
  def buildProviderWithGridSize(size: Int) : ComponentProvider = {
    val compContainer = ComponentContainer()
    compContainer.register[Random, RandomMock](Singleton)
    compContainer.register[GridController, GridControllerImpl](Singleton)
    compContainer.register[GridCollector, GridControllerImpl](Singleton)
    compContainer.register[ElementCollector, ElementControllerImpl](Singleton)
    compContainer.register[ElementController, ElementControllerImpl](Singleton)
    compContainer.register[ControllerMediator](Singleton)
    compContainer.register[FocusManager](Singleton)
    compContainer.register[ConsoleGridView](Singleton)
    compContainer.register[GridConfig](()=> GridConfig(size, size), Singleton)
    
    compContainer.buildProvider()
  }
  
  "GridView" when {
    "size 9x9" should {
      "display a 9x9 grid" in {
        
        val gridView = buildProviderWithGridSize(9).get[ConsoleGridView]
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
        val gridView = buildProviderWithGridSize(4).get[ConsoleGridView]
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
        val gridView = buildProviderWithGridSize(6).get[ConsoleGridView]
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
        
        val newProvider = buildProviderWithGridSize(6)
        val gridView = newProvider.get[ConsoleGridView]
        val elementController = newProvider.get[ElementController]
        val mediator = newProvider.get[ControllerMediator]
        
        mediator.setElement(elementController.elements(0), 0)

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
        
        val newProvider = buildProviderWithGridSize(9)
        val gridView = newProvider.get[ConsoleGridView]
        val gridController = newProvider.get[GridController]
        
        val formatter = ComposedConsoleFormatter.create(gridView.consoleElement)

        formatter.select()

        gridController.grid.tiles.head.state should be(blockudoku.models.TileState.blocked)
      }
    }

    "previewing" should {
      "display a green preview when the element can be placed" in {
        val newProvider = buildProviderWithGridSize(6)
        val gridView = newProvider.get[ConsoleGridView]
        val gridController = newProvider.get[GridController]

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
        val newProvider = buildProviderWithGridSize(6)
        val gridView = newProvider.get[ConsoleGridView]
        val gridController = newProvider.get[GridController]
        val mediator = newProvider.get[ControllerMediator]
        val elementController = newProvider.get[ElementController]
        

        mediator.setElement(elementController.elements(0), 0)

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

        val newProvider = buildProviderWithGridSize(6)
        val gridView = newProvider.get[ConsoleGridView]
        val gridController = newProvider.get[GridController]
        

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
