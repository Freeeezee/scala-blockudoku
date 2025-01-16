package blockudoku.saving.serializerYAMLImpl.serialize

case class NumberValue(number : Number) extends Value {
  
  def serialize(indentLevel: Int): String = {
    number.toString
  }
}
