package blockudoku.saving.serializerXMLImpl
import blockudoku.models.Point

object PointSerializer {
    def serialize(point: Point): String = {
        <Point>
            <xPos>{point.xPos}</xPos>
            <yPos>{point.yPos}</yPos>
        </Point>.toString()
    }

    def deserialize(data: String): Point = {
        val xml = scala.xml.XML.loadString(data)
        val xPos = (xml \ "xPos").text.toInt
        val yPos = (xml \ "yPos").text.toInt
        Point(xPos, yPos)
    }
}
