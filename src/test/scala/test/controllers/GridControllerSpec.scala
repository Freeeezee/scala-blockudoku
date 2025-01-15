package test.controllers

import blockudoku.controllers.GridConfig
import blockudoku.controllers.mediatorImpl.{ElementController, GridController}

class GridControllerSpec extends ControllerSpec {
  "A GridController" when {
    "new" should {
      "have a grid as defined in GridConfig" in {
        val gridController = provider.get[GridController]
        val gridConfig = provider.get[GridConfig]
        
        gridController.grid.xLength should be(gridConfig.xLength)
        gridController.grid.yLength should be(gridConfig.yLength)
      }
    }
    
    "an element is added" should {
      "contain the element at the correct position" in {
        val gridController = provider.get[GridController]
        val elementController = provider.get[ElementController]
        val element = elementController.elements.head

        gridController.setElement(element, 0)

        gridController.grid.tiles(0).state should be(blockudoku.models.TileState.blocked)
        gridController.grid.tiles(1).state should be(blockudoku.models.TileState.blocked)
      }
    }
  }

  "A Grid " should {
    "return None if the tile is out of bounds" in {
      val gridController = provider.get[GridController]
      gridController.grid.tile(-1, 0) should be(None)
      gridController.grid.tile(0, -1) should be(None)
      gridController.grid.tile(9, 0) should be(None)
      gridController.grid.tile(0, 9) should be(None)
    }
    "return None if trying to get element tiles out of bounds" in {
      val gridController = provider.get[GridController]
      val elementController = provider.get[ElementController]
      val element = elementController.elements(0)
      gridController.grid.elementTiles(element, 8) should be(None)
    }
  }
}
