package blockudoku.saving.serializerYAMLImpl

import blockudoku.models.Point
import blockudoku.saving.serializerYAMLImpl.deserialize.{IntYamlValue, ObjectYamlValue}
import blockudoku.saving.serializerYAMLImpl.serialize.{KeyValuePair, NumberValue, ObjectValue}

object PointSerializer {
  def serialize(point: Point): ObjectValue = {
    val xPos = KeyValuePair("xPos", NumberValue(point.xPos))
    val yPos = KeyValuePair("yPos", NumberValue(point.yPos))
    
    ObjectValue(List(xPos, yPos))
  }
  
  def deserialize(obj: ObjectYamlValue): Point = {
    val xPos = obj.get[IntYamlValue]("xPos").value
    val yPos = obj.get[IntYamlValue]("yPos").value
    
    Point(xPos, yPos)
  }
}
