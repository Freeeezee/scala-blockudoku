package test.controllers

import blockudoku.controllers.mediatorImpl.ElementController
import blockudoku.models.{Element, Point}

class ElementControllerSpec extends ControllerSpec {
  "An ElementController" when {
    "new" should {
      "have 3 random elements with points at (1,0) and (0,0)" in {
        val elementController = provider.get[ElementController]
        elementController.elements should not be null
        elementController.elements.length should be (3)
        val element = elementController.elements.head
        
        elementShouldHaveExpectedStructure(element)
      }
    }
    "regenerate is called on slot 0" should {
      "have a new element at slot 0" in {
        val elementController = provider.get[ElementController]
        val element = elementController.regenerate(0)
        
        elementShouldHaveExpectedStructure(element)
      }
    }
  }
  
  private def elementShouldHaveExpectedStructure(element: Element): Unit = {
    element.structure.length should be(2)
    element.structure.head should equal(Point(1, 0))
    element.structure(1) should equal(Point(0, 0))
  }
}
