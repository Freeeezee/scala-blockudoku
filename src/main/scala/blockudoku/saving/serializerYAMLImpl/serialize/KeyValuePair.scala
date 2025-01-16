package blockudoku.saving.serializerYAMLImpl.serialize

case class KeyValuePair(key: String, value: Value) extends Value {
    
    def serialize(indentLevel: Int): String = {
      val str = new StringBuilder
      str.append(key)
      str.append(": ")
      str.append(value.serialize(indentLevel))
      str.toString
    }
}
