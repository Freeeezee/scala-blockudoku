package blockudoku.saving.serializerXMLImpl

import blockudoku.models.{Tile, TileState}

import scala.xml.{Elem, Node}

object TileSerializer {

  def serialize(tile: Tile): Elem = {
    <Tile>
      <Index>{tile.index}</Index>
      <Position>
        {PointSerializer.serialize(tile.position)}
      </Position>
      <Colors>{tile.colors}</Colors>
      <State>{tile.state}</State>
    </Tile>
  }

  def deserialize(data: Node): Tile = {
    val index = (data \ "Index").text.toInt
    val position = PointSerializer.deserialize(((data \ "Position") \ "Point").head)
    val colors = (data \ "Colors").text.toInt
    val stateText = (data \ "State").text
    val state = TileState.valueOf(stateText)
    Tile(index, position, colors, state)
  }
}
