package blockudoku.saving.serializerXMLImpl

import blockudoku.models.Element
import blockudoku.models.Colors

import scala.xml.{Elem, Node}

object ElementSerializer {
  
  def serialize(element: Element): Elem = {
    <Element>
      <Slot>{element.slot}</Slot>
      <Colors>{element.colors}</Colors>
      <Structure>
        {element.structure.map(PointSerializer.serialize)}
      </Structure>
    </Element>
  }
  
  def deserialize(data: Node): Element = {
    val slot = (data \ "Slot").text.toInt
    val colorsText = (data \ "Colors").text
    val colors = Colors.valueOf(colorsText)
    val structure = ((data \ "Structure") \ "Point").map(node => PointSerializer.deserialize(node))
    Element(structure.toList, slot, colors)
  }
}
