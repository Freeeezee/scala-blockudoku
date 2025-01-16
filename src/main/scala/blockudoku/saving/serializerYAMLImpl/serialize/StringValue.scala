package blockudoku.saving.serializerYAMLImpl.serialize

import blockudoku.saving.serializerYAMLImpl.Value

case class StringValue(string: String) extends Value {
  
  def serialize(): String = {
    string
  }

  def deserialize(data: String): StringValue = {
    
  }
}
