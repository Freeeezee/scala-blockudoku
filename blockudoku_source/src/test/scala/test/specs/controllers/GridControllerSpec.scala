package test.specs.controllers

import blockudoku.controllers.GridController
import test.specs.UnitSpec

class GridControllerSpec extends UnitSpec {
  "A GridController" >> {
    "when new" >> {
      "should have a 9x9 grid" >> {
        val controller = new GridController(9, 9)
        controller.grid should be_!=(null)
        controller.grid.xLength should beEqualTo(9)
        controller.grid.yLength should beEqualTo(9)
      }
    }
  }
}
