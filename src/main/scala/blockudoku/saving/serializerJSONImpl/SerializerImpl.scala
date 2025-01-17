package blockudoku.saving.serializerJSONImpl

import blockudoku.controllers.mediatorImpl.{ElementController, GridController}
import blockudoku.controllers.{ElementCollector, GridCollector, ScoreCollector, ScoreController}
import blockudoku.models.{Element, Grid}
import blockudoku.saving.Serializer
import blockudoku.saving.serializerJSONImpl.ModelSerializer.{elementReads, elementWrites, gridReads, gridWrites}
import play.api.libs.json.Json


class SerializerImpl(gridCollector: GridCollector, elementCollector: ElementCollector, elementController: ElementController, gridController: GridController, scoreCollector: ScoreCollector, scoreController: ScoreController) extends Serializer {

    override def serialize(): String = {
      val elements = elementCollector.getElements
      val grid = gridCollector.getGrid.copy()

      val jsonElements = Json.toJson(elements)
      val jsonGrid = Json.toJson(grid)

      val json = Json.obj(
        "Elements" -> jsonElements,
        "Grid" -> jsonGrid,
        "Score" -> scoreCollector.getScore
      )
      Json.stringify(json)
    }

    override def deserialize(data: String): Unit = {
      val json = Json.parse(data)
      val elements = (json \ "Elements").as[List[Element]]
      val grid = (json \ "Grid").as[Grid]
      val score = (json \ "Score").as[Int]

      elementController.loadElements(elements)
      gridController.loadGrid(grid)
      scoreController.loadScore(score)
    }
}
