package blockudoku.controllers

import blockudoku.models.{Element, Point}
import blockudoku.services.Random
import blockudoku.windows.{FocusManager, FocusState}

class ElementController(random: Random, focusManager: FocusManager) {
  val maxElementLength: Int = 3
  val elementCount: Int = 3
  
  val elements: Array[Element] = generateInitialElements
  
  var selectedElement: Option[Element] = None

  def regenerate(slot: Int): Element = {
    if slot >= elementCount then throw new IndexOutOfBoundsException("Slot must be smaller than element count.")

    elements(slot) = generateElement(slot)

    elements(slot)
  }

  private def generateInitialElements: Array[Element] = {
    val array: Array[Element] = new Array[Element](elementCount)

    for i <- 0 until elementCount do
      array(i) = generateElement(i)

    array
  }

  private def generateElement(slot: Int): Element = {
    var points = List[Point](Point(0, 0))
    val length = random.between(1, maxElementLength)

    for i <- 0 until length do
      points = generateNextPoint(points) :: points

    Element(points, slot)
  }

  private def generateNextPoint(points: List[Point]): Point = {
    val possiblePoints = (0 to 7).toList
      .map(num => pointFromDirection(points.last, num))
      .filter(point => !points.contains(point))

    // This is probably impossible
    if (possiblePoints.isEmpty) throw new Exception("Point generation failed to find a possible next point.")

    possiblePoints(random.nextInt(possiblePoints.length))
  }

  private def pointFromDirection(point: Point, direction: Int): Point = {
    direction match
      case 0 => Point(point.xPos + 1, point.yPos) // east
      case 1 => Point(point.xPos + 1, point.yPos - 1) // south-east
      case 2 => Point(point.xPos, point.yPos - 1) // south
      case 3 => Point(point.xPos - 1, point.yPos - 1) // south-west
      case 4 => Point(point.xPos - 1, point.yPos) // west
      case 5 => Point(point.xPos - 1, point.yPos + 1) // north-west
      case 6 => Point(point.xPos, point.yPos + 1) // north
      case 7 => Point(point.xPos + 1, point.yPos + 1) // north-east
  }
  
  def selectElement(element: Element): Unit = {
    selectedElement = Some(element)
    
    focusManager.focusState = FocusState.Grid
  }
}
