package test.controllers

import blockudoku.controllers.GridController
import test.UnitSpec

class GridControllerSpec extends UnitSpec {
  "A GridController" when {
    "new" should {
      "have a 9x9 grid" in {
        val controller = new GridController()
        controller.grid should not be null
        controller.grid.xLength should be(9)
        controller.grid.yLength should be(9)
      }
    }
  }
}
