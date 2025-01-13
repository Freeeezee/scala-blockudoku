package blockudoku.saving.serializerXMLImpl

import blockudoku.models.Grid

object GridSerializer {

  def serialize(grid: Grid): String = {
    <Grid>
      <xLen>{grid.xLength}</xLen>
      <yLen>{grid.yLength}</yLen>
      <Tiles>
        {grid.tiles.map(TileSerializer.serialize)}
      </Tiles>
    </Grid>.toString()
  }
  def deserialize(data: String): Grid = {
    val xml = scala.xml.XML.loadString(data)
    val xLen = (xml \ "xLen").text.toInt
    val yLen = (xml \ "yLen").text.toInt
    val tiles = (xml \ "Tiles").map(node => TileSerializer.deserialize(node.text))
    Grid(xLen, yLen)(tiles.toList)
  }
}
