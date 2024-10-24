package test.controllers

import blockudoku.controllers.ElementController
import blockudoku.models.Point
import test.{RandomMock, UnitSpec}

class ElementControllerSpec extends UnitSpec {
  info("The ElementController is responsible for managing the placeable elements in the game.")
  info("It should generate initial elements and regenerate them once they are placed on the grid.")
  
  Feature("ElementController") {
    Scenario("generate initial elements") {
      Given("a new ElementController")
      val controller = ElementController(RandomMock())
      
      Then("the ElementController should generate a list of initial elements")
      controller.elements should not be null
      controller.elements.length should be(3)

      val element = controller.elements(0)
      element.structure should not be null
      element.structure.length should be(2)
      element.structure(0) should equal(Point(1, 0))
      element.structure(1) should equal(Point(0, 0))
    }
    Scenario("regenerate elements") {
      Given("an ElementController")
      val controller = ElementController(RandomMock())
      
      When("regenerating element at slot 0")
      controller.regenerate(0)
      
      Then("controller should have a new element at slot 0")
      val element = controller.elements(0)
      element.structure should not be null
      element.structure.length should be(2)
      element.structure(0) should equal(Point(1, 0))
      element.structure(1) should equal(Point(0, 0))
    }
  }
}
