package blockudoku.saving.serializerXMLImpl

import blockudoku.models.Grid

import scala.xml.{Elem, Node}

object GridSerializer {

  def serialize(grid: Grid): Elem = {
    <Grid>
      <xLen>{grid.xLength}</xLen>
      <yLen>{grid.yLength}</yLen>
      <Tiles>
        {grid.tiles.map(TileSerializer.serialize)}
      </Tiles>
    </Grid>
  }
  
  def deserialize(data: Node): Grid = {
    val gridData = data \ "Grid"
    val xLen = (gridData \ "xLen").text.toInt
    val yLen = (gridData \ "yLen").text.toInt
    val tiles = ((gridData \ "Tiles") \ "Tile").map(node => TileSerializer.deserialize(node))
    Grid(xLen, yLen)(tiles.toList)
  }
}
