package blockudoku.saving.serializerYAMLImpl

import blockudoku.models.Grid
import blockudoku.saving.serializerYAMLImpl.deserialize.{ArrayYamlValue, IntYamlValue, ObjectYamlValue}
import blockudoku.saving.serializerYAMLImpl.serialize.{ArrayValue, KeyValuePair, NumberValue, ObjectValue, Value}

object GridSerializer {
  def serialize(grid: Grid): ObjectValue = {
    val xLength = KeyValuePair("xLength", NumberValue(grid.xLength))
    val yLength = KeyValuePair("yLength", NumberValue(grid.yLength))
    
    var tiles = Seq[Value]()
    
    for index <- grid.tiles.indices do {
      tiles = tiles :+ KeyValuePair(index.toString, TileSerializer.serialize(grid.tiles(index)))
    }
    
    val tilesValue = KeyValuePair("tiles", ArrayValue(tiles))
    
    ObjectValue(List(xLength, yLength, tilesValue))
  }
  
  def deserialize(obj: ObjectYamlValue): Grid = {
    val xLength = obj.get[IntYamlValue]("xLength").value
    val yLength = obj.get[IntYamlValue]("yLength").value
    
    val tilesArray = obj.get[ArrayYamlValue]("tiles").value
    val tiles = tilesArray.map(tile => TileSerializer.deserialize(tile.asInstanceOf[ObjectYamlValue]))
    
    Grid(xLength, yLength, tiles)
  }
}
