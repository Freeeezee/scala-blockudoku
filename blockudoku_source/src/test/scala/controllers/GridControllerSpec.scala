package controllers

import blockudoku.controllers.GridController
import test.UnitSpec

class GridControllerSpec extends UnitSpec {
  "A GridController" when {
    "new" should {
      "have a grid" in {
        val controller = new GridController()
        controller.grid should not be null
      }
    }
  }
}
