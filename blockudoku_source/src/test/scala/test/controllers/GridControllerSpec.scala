package test.controllers

import org.scalatest.Assertion
import test.UnitSpec

class GridControllerSpec extends UnitSpec {
  object `A GridController` {
    object `when new` {
      def `should have a 9x9 grid`: Assertion = {
        val controller = new blockudoku.controllers.GridController(9, 9)
        controller.grid should not be null
        controller.grid.xLength should be(9)
        controller.grid.yLength should be(9)
      }
    }
  }
}
