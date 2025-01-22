package blockudoku.saving.serializerXMLImpl

import blockudoku.controllers.mediatorImpl.{ElementController, GridController, ScoreController}
import blockudoku.controllers.{ElementCollector, GridCollector, ScoreCollector}
import blockudoku.saving.Serializer

class SerializerImpl(gridCollector: GridCollector, elementCollector: ElementCollector, elementController: ElementController, gridController: GridController, scoreCollector: ScoreCollector, scoreController: ScoreController) extends Serializer {

    override def serialize(): String = {
        val elements = elementCollector.getElements
        val grid = gridCollector.getGrid.copy()
        
        <GameState>
            <Elements>
                {elements.map(ElementSerializer.serialize)}
            </Elements>
            <Grid>
                {GridSerializer.serialize(grid)}
            </Grid>
            {ScoreSerializer.serialize(scoreController.getScore)}
        </GameState>.toString
    }

    override def deserialize(data: String): Unit = {
        val xml = scala.xml.XML.loadString(data)
        val elements = ((xml \ "Elements") \ "Element").map(node => ElementSerializer.deserialize(node))
        val grid = GridSerializer.deserialize((xml \ "Grid").head)
        val score = ScoreSerializer.deserialize(xml)
        
        elementController.loadElements(elements.toList)
        gridController.loadGrid(grid)
        scoreController.loadScore(score)
    }
}
