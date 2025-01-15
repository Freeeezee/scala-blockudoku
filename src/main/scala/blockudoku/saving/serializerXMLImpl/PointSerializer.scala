package blockudoku.saving.serializerXMLImpl
import blockudoku.models.Point

import scala.xml.{Elem, Node}

object PointSerializer {
    def serialize(point: Point): Elem = {
        <Point>
            <xPos>{point.xPos}</xPos>
            <yPos>{point.yPos}</yPos>
        </Point>
    }

    def deserialize(data: Node): Point = {
        val xPos = (data \ "xPos").text.toInt
        val yPos = (data \ "yPos").text.toInt
        Point(xPos, yPos)
    }
}
