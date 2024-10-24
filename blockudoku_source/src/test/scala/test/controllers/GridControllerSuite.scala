package test.controllers

import blockudoku.controllers.GridController
import test.UnitSuite

class GridControllerSuite extends UnitSuite {
  test("A GridController when new should have a 9x9 grid") {
    val controller = new GridController(9, 9)
    controller.grid should not be null
    controller.grid.xLength should be(9)
    controller.grid.yLength should be(9)
  }
}
