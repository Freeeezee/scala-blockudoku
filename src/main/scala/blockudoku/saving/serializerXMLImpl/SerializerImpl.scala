package blockudoku.saving.serializerXMLImpl

import blockudoku.controllers.mediatorImpl.{ElementController, GridController}
import blockudoku.controllers.{ElementCollector, GridCollector}
import blockudoku.saving.Serializer

class SerializerImpl(gridCollector: GridCollector, elementCollector: ElementCollector, elementController: ElementController, gridController: GridController) extends Serializer {

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
        </GameState>.toString
    }

    override def deserialize(data: String): Unit = {
        val xml = scala.xml.XML.loadString(data)
        val elements = ((xml \ "Elements") \ "Element").map(node => ElementSerializer.deserialize(node))
        val grid = GridSerializer.deserialize((xml \ "Grid").head)
        
        elementController.elements = elements.toList
        gridController.grid = grid
        
        gridController.createSnapshot()
        elementController.createSnapshot()
    }
}
