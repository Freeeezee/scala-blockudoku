import blockudoku.models.Point
import scala.util.Random

case class Element(structure: List[Point]) {
  def dimensions: (Int, Int, Int, Int) = {
    val xMin = structure.map(_.xPos).min
    val xMax = structure.map(_.xPos).max
    val yMin = structure.map(_.yPos).min
    val yMax = structure.map(_.yPos).max

    (xMin, xMax, yMin, yMax)
  }
}

def pointFromDirection(point: Point, direction: Int): Point = {
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

def generateNextPoint(points: List[Point]): Point = {
  val possiblePoints = (0 to 7).toList
    .map(num => pointFromDirection(points.last, num))
    .filter(point => !points.contains(point))

  // This is probably impossible
  if (possiblePoints.isEmpty) throw new Exception("Point generation failed to find a possible next point.")

  possiblePoints(Random.nextInt(possiblePoints.length))
}

def generateElement(length: Int): Element = {
  var points = List[Point](Point(0, 0))

  for i <- 0 until length do
    points = points :+ generateNextPoint(points)

  Element(points)
}

val element = generateElement(3)

val (xMin, xMax, yMin, yMax) = element.dimensions

val builder = new StringBuilder

for y <- yMin to yMax do
  for x <- xMin to xMax do
    if element.structure.contains(Point(x, y)) then builder.append("x")
    else builder.append(" ")
  builder.append("\n")

s"\n${builder.result()}"


