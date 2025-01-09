package blockudoku.services.gridPreviewBuilderImpl

import blockudoku.controllers.mediatorImpl.{ElementController, GridController}
import blockudoku.controllers.{ElementCollector, GridCollector}
import blockudoku.models.TileState.{blocked, empty, previewInvalid, previewValid}
import blockudoku.models.{Grid, Tile}
import blockudoku.services.GridPreviewBuilder
import com.google.inject.Inject

class GridPreviewBuilderImpl @Inject (gridCollector: GridCollector, elementCollector: ElementCollector) extends GridPreviewBuilder {
  def buildGrid(selectedPos: Int): Grid = {
    var grid = gridCollector.getGrid.copy()

    if selectedPos < 0 || elementCollector.getSelectedElement.isEmpty then return grid
    
    val tiles = grid.elementTiles(elementCollector.getSelectedElement.get, selectedPos)

    if tiles.isEmpty then return grid

    var blockedTiles = List[Tile]()
    var emptyTiles = List[Tile]()

    tiles.get.foreach { tile =>
      if tile.state == empty then emptyTiles = emptyTiles :+ tile
      else if tile.state == blocked then blockedTiles = blockedTiles :+ tile
    }

    grid = grid.copyWithNewState(emptyTiles, previewValid)
    grid = grid.copyWithNewState(blockedTiles, previewInvalid)

    grid
  }
}
