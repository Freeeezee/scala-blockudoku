package test.controllers

import blockudoku.controllers.GridController
import test.UnitSpec

class GridControllerSpec extends UnitSpec {
  private val controllers = Table(
    ("controller", "xLength", "yLength"),
    (new GridController(9, 9), 9, 9),
    (new GridController(16, 16), 16, 16),
    (new GridController(25, 25), 25, 25)
  )

  property("A GridController should have a grid with equal dimensions to the ones passed in the constructor") {
    forAll(controllers) { (controller, xLength, yLength) =>
          controller.grid should not be null
          controller.grid.xLength should be(xLength)
          controller.grid.yLength should be(yLength)
    }
  }
}
