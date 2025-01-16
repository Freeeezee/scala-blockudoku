package blockudoku.saving.serializerYAMLImpl

import blockudoku.controllers.mediatorImpl.{ElementController, GridController}
import blockudoku.controllers.{ElementCollector, GridCollector}
import blockudoku.saving.Serializer
import blockudoku.saving.serializerYAMLImpl.deserialize.{ArrayYamlValue, ObjectYamlValue, YamlParser}
import blockudoku.saving.serializerYAMLImpl.serialize.{ArrayValue, KeyValuePair, ObjectValue, Value}

class SerializerImpl(elementCollector: ElementCollector, gridCollector: GridCollector, elementController: ElementController, gridController: GridController) extends Serializer {

  override def serialize(): String = {
    val elements = elementCollector.getElements
    val grid = gridCollector.getGrid.copy()

    var elementsArray = List[Value]()

    for index <- elements.indices do {
      elementsArray = elementsArray :+ KeyValuePair(index.toString, ElementSerializer.serialize(elements(index)))
    }

    val elementsKV = KeyValuePair("Elements", ArrayValue(elementsArray))
    val gridKV = KeyValuePair("Grid", GridSerializer.serialize(grid))

    val gameState = ObjectValue(List(elementsKV, gridKV))
    val gameStateKV = KeyValuePair("GameState", gameState)

    gameStateKV.serialize()
  }

  override def deserialize(data: String): Unit = {
    val gameState = YamlParser.parse(data)("GameState").asInstanceOf[ObjectYamlValue]

    val elementsArray = gameState.get[ArrayYamlValue]("Elements")
    val elements = elementsArray.value.map(value => ElementSerializer.deserialize(value.asInstanceOf[ObjectYamlValue]))

    val gridObject = gameState.get[ObjectYamlValue]("Grid")
    val grid = GridSerializer.deserialize(gridObject)

    elementController.loadElements(elements)
    gridController.loadGrid(grid)
  }
}
