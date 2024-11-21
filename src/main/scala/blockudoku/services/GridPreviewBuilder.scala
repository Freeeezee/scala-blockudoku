package blockudoku.services

import blockudoku.controllers.{ElementController, GridController}
import blockudoku.models.Grid
import blockudoku.models.TileState.{blocked, empty, previewInvalid, previewValid}

class GridPreviewBuilder(gridController: GridController, elementController: ElementController) {
  def buildGrid(selectedPos: Int): Grid = {
    val grid = gridController.grid.copy()

    if selectedPos < 0 || elementController.selectedElement.isEmpty then return grid

    val tiles = grid.elementTiles(elementController.selectedElement.get, selectedPos)

    if tiles.isEmpty then return grid

    tiles.get.foreach { tile => 
      if tile.state == empty then tile.state = previewValid
      else if tile.state == blocked then tile.state = previewInvalid
    }
    
    grid
  }
}
