package blockudoku.saving.serializerJSONImpl

import blockudoku.controllers.mediatorImpl.{ElementController, GridController}
import blockudoku.controllers.{ElementCollector, GridCollector}
import blockudoku.models.{Element, Grid}
import blockudoku.saving.Serializer
import play.api.libs.json.Json
import blockudoku.saving.serializerJSONImpl.ModelSerializer.gridReads
import blockudoku.saving.serializerJSONImpl.ModelSerializer.gridWrites
import blockudoku.saving.serializerJSONImpl.ModelSerializer.elementReads
import blockudoku.saving.serializerJSONImpl.ModelSerializer.elementWrites


class SerializerImpl(gridCollector: GridCollector, elementCollector: ElementCollector, elementController: ElementController, gridController: GridController) extends Serializer {

    override def serialize(): String = {
      val elements = elementCollector.getElements
      val grid = gridCollector.getGrid.copy()

      val jsonElements = Json.toJson(elements)
      val jsonGrid = Json.toJson(grid)

      val json = Json.obj(
        "Elements" -> jsonElements,
        "Grid" -> jsonGrid
      )
      Json.stringify(json)
    }

    override def deserialize(data: String): Unit = {
      val json = Json.parse(data)
      val elements = (json \ "Elements").as[List[Element]]
      val grid = (json \ "Grid").as[Grid]

      elementController.loadElements(elements)
      gridController.loadGrid(grid)
    }
}
