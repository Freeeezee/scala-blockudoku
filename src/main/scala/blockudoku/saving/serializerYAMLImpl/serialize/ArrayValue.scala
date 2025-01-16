package blockudoku.saving.serializerYAMLImpl.serialize

import blockudoku.saving.serializerYAMLImpl.Value

case class ArrayValue(array : Seq[Value]) extends Value {
  
  def serialize(indentLevel: Int): String = {
    val str = new StringBuilder
    str.append("\n")
    array.foreach(value => {
      str.append("  " * (indentLevel + 1))
      str.append("- ")
      str.append(value.serialize(indentLevel + 1))
      str.append("\n")
    })
    str.toString
  }

  def deserialize(data: String): ArrayValue = {
    
  }
}
