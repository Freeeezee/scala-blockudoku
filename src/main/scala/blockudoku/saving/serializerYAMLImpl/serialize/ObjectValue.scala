package blockudoku.saving.serializerYAMLImpl.serialize

case class ObjectValue(keyValue : Seq[KeyValuePair]) extends Value {
  def serialize(indentLevel: Int): String = {
    val str = new StringBuilder
    keyValue.foreach(value => {
      str.append("\n")
      str.append("  " * (indentLevel + 1))
      str.append(value.serialize(indentLevel + 1))
    })
    
    str.toString
  }
}
