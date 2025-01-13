package blockudoku.saving.serializerXMLImpl

import blockudoku.models.Element
import blockudoku.models.Colors

object ElementSerializer {
  
  def serialize(element: Element): String = {
    <Element>
      <Slot>{element.slot}</Slot>
      <Colors>{element.colors}</Colors>
      <Structure>
        {element.structure.map(PointSerializer.serialize)}
      </Structure>
    </Element>.toString
  }
  
  def deserialize(data: String): Element = {
    val xml = scala.xml.XML.loadString(data)
    val slot = (xml \ "Slot").text.toInt
    val colorsText = (xml \ "Colors").text
    val colors = Colors.valueOf(colorsText)
    val structure = (xml \ "Structure").map(node => PointSerializer.deserialize(node.text))
    Element(structure.toList, slot, colors)
  }
}
