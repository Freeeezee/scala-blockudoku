package blockudoku.saving.serializerYAMLImpl.serialize

trait Value {
  
  def serialize(indentLevel: Int = 0): String
  
}
