package test.controllers

import blockudoku.controllers.GridController
import test.UnitSpec
class GridControllerSpec extends UnitSpec {
  describe("A Gridcontroller") {
    describe("when new") {
      it("should have a 9x9 grid") {
        val controller = new GridController(9, 9)
        controller.grid should not be null
        controller.grid.xLength should be(9)
        controller.grid.yLength should be(9)
      }
    }
  }
}
