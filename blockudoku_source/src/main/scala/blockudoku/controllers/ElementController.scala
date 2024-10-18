package blockudoku.controllers

import blockudoku.models.{Element, Point}

import scala.util.Random

case class ElementController() { // case-class -> Verwendung bei immutable objects
  val maxElementLength: Int = 3
  val elementCount: Int = 3
  
  val elements: Array[Element] = generateInitialElements

  def regenerate(slot: Int): Element = {
    if slot >= elementCount then throw new IndexOutOfBoundsException("Slot must be smaller than element count.")

    elements(slot) = generateElement

    elements(slot) // return
  }

  private def generateInitialElements: Array[Element] = {
    val array: Array[Element] = new Array[Element](elementCount)
    /* Inhalte des immutable Containers array lassen sich verändern
    * (elementCount): Größe
    */

    for i <- 0 until elementCount do
      array(i) = generateElement

    array
  }

  private def generateElement: Element = {
    var points = List[Point](Point(0, 0)) //unveränderliche Liste, daher kein Array
    val length = Random.between(1, maxElementLength)

    for i <- 0 until length do
      points = generateNextPoint(points) :: points // :: = fügt Element am Anfang ein
    Element(points)
  }

  private def generateNextPoint(points: List[Point]): Point = {
    val possiblePoints = (0 to 7).toList // 0-7 = mögliche Richtungen
      .map(num => pointFromDirection(points.last, num)) //points.last = letzten Punkt aus Liste, num = Angabe der Richtung des neuen Punktes
      .filter(point => !points.contains(point)) // Duplikate vermeiden (filtert exisitierende Punkte raus?

    // This is probably impossible
    if (possiblePoints.isEmpty) throw new Exception("Point generation failed to find a possible next point.")

    possiblePoints(Random.nextInt(possiblePoints.length))
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
}
