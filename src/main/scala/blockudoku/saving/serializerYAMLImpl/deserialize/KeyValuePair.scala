package blockudoku.saving.serializerYAMLImpl.deserialize

case class KeyValuePair(key: String, value: String, multiLine: Boolean) {
  
  def interpretValue: Value = {
    if (multiLine) {
      ObjectValue(value)
    } else {
      StringValue(value)
    }
  }
}
