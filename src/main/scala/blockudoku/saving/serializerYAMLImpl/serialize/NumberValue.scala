package blockudoku.saving.serializerYAMLImpl.serialize

import blockudoku.saving.serializerYAMLImpl.Value

case class NumberValue(number : Number) extends Value {
  
  def serialize(): String = {
    number.toString
  }

  def deserialize(data: String): NumberValue = {
    
  }
}
