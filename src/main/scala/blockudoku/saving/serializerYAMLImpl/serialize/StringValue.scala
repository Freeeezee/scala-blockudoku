package blockudoku.saving.serializerYAMLImpl.serialize

case class StringValue(string: String) extends Value {
  
  def serialize(indentValue: Int): String = {
    string
  }
}
