package test.controllers

import blockudoku.controllers.GridController
import org.junit.Assert._
import org.junit.Test
import test.UnitSpec

class GridControllerSpec extends UnitSpec{
  @Test
  def gridController_new_have_a_9x9_grid(): Unit = {
    val controller = new GridController(9, 9)

    assert(controller.grid != null)
    assert(controller.grid.xLength == 9)
    assert(controller.grid.yLength == 9)
  }
}
