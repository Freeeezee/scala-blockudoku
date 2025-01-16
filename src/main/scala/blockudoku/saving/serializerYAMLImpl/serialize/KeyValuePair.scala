package blockudoku.saving.serializerYAMLImpl.serialize

import blockudoku.saving.serializerYAMLImpl.serialize.Value

case class KeyValuePair(key: String, value: Value) {
    
    def serialize(indentLevel: Int): String = {
      val str = new StringBuilder
      str.append(key)
      str.append(": ")
      str.append(value.serialize(indentLevel))
      str.toString
    }
}
