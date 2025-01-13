package blockudoku.saving.serializerXMLImpl

import blockudoku.controllers.{ElementCollector, GridCollector}
import blockudoku.models.Point
import blockudoku.saving.Serializer

class SerializerXMLImpl(gridCollector: GridCollector, elementCollector: ElementCollector) extends Serializer {

    //Collector nutzen
    val elements = elementCollector.getElements
    //val colors = elementCollector.getElements.map(_.colors)
    val grid = gridCollector.getGrid.copy()
    val tiles = grid.elementTiles
    // val points = ?


    override def serialize(): String = {
        // colors, element, grid, point, tile
        val sb = new StringBuilder
        //sb.append(ColorsSerializer.serialize(colors))
        //sb.append(PointSerializer.serialize())
        sb.append(elements.foreach(ElementSerializer.serialize))
        //sb.append(tiles match {
        //    case Some(list) => TileSerializer.serialize(tiles.Tile)
        //    case None => ""
        //})
        sb.append(GridSerializer.serialize(grid))
        
        val result = sb.toString()
        result
    }

    override def deserialize(data: String): Unit = {

    }
}
