package blockudoku.saving.serializerYAMLImpl

import blockudoku.models.{Tile, TileState}
import blockudoku.saving.serializerYAMLImpl.deserialize.{IntYamlValue, ObjectYamlValue, StringYamlValue}
import blockudoku.saving.serializerYAMLImpl.serialize.{KeyValuePair, NumberValue, ObjectValue, StringValue}

object TileSerializer {
  def serialize(tile: Tile): ObjectValue = {
    val index = KeyValuePair("index", NumberValue(tile.index))
    val position = KeyValuePair("position", PointSerializer.serialize(tile.position))
    val colors = KeyValuePair("colors", NumberValue(tile.colors))
    val state = KeyValuePair("state", StringValue(tile.state.toString))

    ObjectValue(List(index, position, colors, state))
  }

  def deserialize(obj: ObjectYamlValue): Tile = {
    val index = obj.get[IntYamlValue]("index").value
    val position = PointSerializer.deserialize(obj.get[ObjectYamlValue]("position"))
    val colors = obj.get[IntYamlValue]("colors").value
    val stateName = obj.get[StringYamlValue]("state").value

    val state = TileState.valueOf(stateName)

    Tile(index, position, colors, state)
  }
}
