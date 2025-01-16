package blockudoku.saving.serializerYAMLImpl.serialize

import blockudoku.saving.serializerYAMLImpl.serialize.Value

case class StringValue(string: String) extends Value {
  
  def serialize(indentValue: Int): String = {
    string
  }
}
