package blockudoku.saving.serializerYAMLImpl.serialize

case class ArrayValue(array : Seq[Value]) extends Value {
  
  def serialize(indentLevel: Int): String = {
    val str = new StringBuilder
    array.foreach(value => {
      str.append("\n")
      str.append("  " * (indentLevel + 1))
      str.append("- ")
      str.append(value.serialize(indentLevel + 2))
    })
    str.toString
  }
}
