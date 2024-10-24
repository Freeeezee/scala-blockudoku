package test.controller

import blockudoku.controllers.GridController
import test.UnitSpec
class GridControllerSpec extends UnitSpec {
  "A GridController" - {
    "new" - {
      "have a 9x9 grid" in {
        val controller = new GridController(9, 9)
        controller.grid should not be null
        controller.grid.xLength should be(9)
        controller.grid.yLength should be(9)
      }
    }
  }

}
