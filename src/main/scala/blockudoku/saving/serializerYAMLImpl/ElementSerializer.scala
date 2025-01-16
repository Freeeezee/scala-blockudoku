package blockudoku.saving.serializerYAMLImpl

import blockudoku.models.Element
import blockudoku.saving.serializerYAMLImpl.deserialize.{ArrayYamlValue, IntYamlValue, ObjectYamlValue}
import blockudoku.saving.serializerYAMLImpl.serialize.{ArrayValue, KeyValuePair, NumberValue, ObjectValue, Value}

object ElementSerializer {
  def serialize(element: Element): ObjectValue = {
    val slot = KeyValuePair("slot", NumberValue(element.slot))
    val colors = KeyValuePair("colors", NumberValue(element.colors))
    
    var structure = Seq[Value]()
    
    for index <- element.structure.indices do {
      structure = structure :+ KeyValuePair(index.toString, PointSerializer.serialize(element.structure(index)))
    }
    
    val structureValue = KeyValuePair("structure", ArrayValue(structure))

    ObjectValue(List(slot, colors, structureValue))
  }

  def deserialize(obj: ObjectYamlValue): Element = {
    val slot = obj.get[IntYamlValue]("slot").value
    val colors = obj.get[IntYamlValue]("colors").value

    val structureArray = obj.get[ArrayYamlValue]("structure").value

    val structure = structureArray.map(point => PointSerializer.deserialize(point.asInstanceOf[ObjectYamlValue]))

    Element(structure, slot, colors)
  }
}
