package blockudoku.saving.serializerYAMLImpl.serialize

import blockudoku.saving.serializerYAMLImpl.serialize.Value

case class NumberValue(number : Number) extends Value {
  
  def serialize(indentLevel: Int): String = {
    number.toString
  }
}
