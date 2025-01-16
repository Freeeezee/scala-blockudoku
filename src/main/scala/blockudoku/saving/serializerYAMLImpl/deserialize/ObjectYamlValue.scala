package blockudoku.saving.serializerYAMLImpl.deserialize

case class ObjectYamlValue(map: Map[String, YamlValue]) extends YamlValue {
  def get[T <: YamlValue](key: String): T  = map(key).asInstanceOf[T]
}
