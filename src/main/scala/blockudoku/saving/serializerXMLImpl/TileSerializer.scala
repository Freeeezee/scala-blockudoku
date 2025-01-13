package blockudoku.saving.serializerXMLImpl

import blockudoku.models.{Colors, Tile, TileState}

object TileSerializer {

  def serialize(tile: Tile): String = {
    <Tile>
      <Index>
        {tile.index}
      </Index>
      <Position>
        {PointSerializer.serialize(tile.position)}
      </Position>
      <Colors>
        {tile.colors}
      </Colors>
      <State>
        {tile.state}
      </State>
    </Tile>.toString()
  } // = Eminence for Color?

  def deserialize(data: String): Tile = {
    val xml = scala.xml.XML.loadString(data)
    val index = (xml \ "Index").text.toInt
    val position = PointSerializer.deserialize((xml \ "Position").text)
    val colorsText = (xml \ "Colors").text
    val colors = Colors.valueOf(colorsText)
    val stateText = (xml \ "State").text
    val state = TileState.valueOf(stateText)
    Tile(index, position, colors, state)
  }
}
