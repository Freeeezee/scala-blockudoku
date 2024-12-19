package test.controllers

import blockudoku.controllers.{ElementController, GridController}
import test.UnitSpec

class GridControllerSpec extends UnitSpec {
  "A GridController" when {
    "new" should {
      "have a 9x9 grid" in {
        val gridController = provider.get[GridController]
        gridController.grid should not be null
        gridController.grid.xLength should be(9)
        gridController.grid.yLength should be(9)
      }
    }
    
    "an element is added" should {
      "contain the element at the correct position" in {
        val gridController = provider.get[GridController]
        val elementController = provider.get[ElementController]
        val element = elementController.elements(0)

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
