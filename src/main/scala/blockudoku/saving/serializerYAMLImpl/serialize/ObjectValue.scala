package blockudoku.saving.serializerYAMLImpl.serialize

import blockudoku.saving.serializerYAMLImpl.Value

case class ObjectValue(keyValue : Seq[KeyValuePair]) extends Value {
  
  
  def serialize(indentLevel: Int): String = {
    val str = new StringBuilder
    str.append("\n")
    keyValue.foreach(value => {
      str.append("  " * (indentLevel + 1))
      str.append(value.serialize(indentLevel + 1))
      str.append("\n")
    })
    
    str.toString
  }

  def deserialize(data: String): ObjectValue = {
    
  }
  
}
