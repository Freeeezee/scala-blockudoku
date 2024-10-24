package test.controllers

import blockudoku.controllers.GridController
import test.UnitSpec

class GridControllerSpec extends UnitSpec {
  info("The GridController is responsible for managing the grid.")
  info("The size of the grid is given to the GridController.")
  
  Feature("GridController") {
    Scenario("Create a 9x9 grid") {
      Given("a GridController with size 9x9")
      val gridController = GridController(9, 9)
      
      Then("the grid should have 81 cells")
      gridController.grid should not be null
      gridController.grid.xLength should be(9)
      gridController.grid.yLength should be(9)
    }
    
    Scenario("Create a 4x4 grid") {
      Given("a GridController with size 4x4")
      val gridController = GridController(4, 4)
      
      Then("the grid should have 16 cells")
      gridController.grid should not be null
      gridController.grid.xLength should be(4)
      gridController.grid.yLength should be(4)
    }
    
    Scenario("Create a 6x6 grid") {
      Given("a GridController with size 6x6")
      val gridController = GridController(6, 6)
      
      Then("the grid should have 36 cells")
      gridController.grid should not be null
      gridController.grid.xLength should be(6)
      gridController.grid.yLength should be(6)
    }
  }
}
