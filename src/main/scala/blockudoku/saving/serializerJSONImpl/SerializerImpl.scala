package blockudoku.saving.serializerJSONImpl

class SerializerImpl extends Serializer {

    override def serialize(): String = {
      val elements = elementCollector.getElements
      val grid = gridCollector.getGrid.copy()

      val jsonElements = Json.toJson(elements)
      val jsonGrid = Json.toJson(grid)

      val json = Json.obj(
        "Elements" -> jsonElements,
        "Grid" -> jsonGrid
      )
    }

    override def deserialize(data: String): Unit = {
      val json = Json.parse(data)
      val elements = (json \ "Elements").as[List[Element]]
      val grid = (json \ "Grid").as[Grid]

      elementController.loadElements(elements.toList)
      gridController.loadGrid(grid)
    }
}
