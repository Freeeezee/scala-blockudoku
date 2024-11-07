package test.controllers

import blockudoku.controllers.{ElementController, GridController}
import blockudoku.windows.FocusManager
import blockudoku.windows.FocusState.Grid
import test.{RandomMock, UnitSpec}

class GridControllerSpec extends UnitSpec {
  "A GridController" when {
    "new" should {
      "have a 9x9 grid" in {
        gridController.grid should not be null
        gridController.grid.xLength should be(9)
        gridController.grid.yLength should be(9)
      }
    }
    
    "an element is added" should {
      "contain the element at the correct position" in {
        val element = elementController.elements(0)

        gridController.setElement(element, 0)

        gridController.grid.tiles(0).state should be(blockudoku.models.TileState.blocked)
        gridController.grid.tiles(1).state should be(blockudoku.models.TileState.blocked)
      }
    }
  }
}
