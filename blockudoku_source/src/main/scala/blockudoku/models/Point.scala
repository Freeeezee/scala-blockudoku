package blockudoku.models

import scala.annotation.targetName

case class Point(xPos : Int, yPos : Int){
  @targetName("add")
  def +(that: Point): Point = Point(this.xPos + that.xPos, this.yPos + that.yPos)
}