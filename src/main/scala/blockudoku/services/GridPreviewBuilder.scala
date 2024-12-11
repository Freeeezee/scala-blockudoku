package blockudoku.services

import blockudoku.controllers.{ElementController, GridController}
import blockudoku.models.{Grid, Tile}
import blockudoku.models.TileState.{blocked, empty, previewInvalid, previewValid}

class GridPreviewBuilder(gridController: GridController, elementController: ElementController) {
  def buildGrid(selectedPos: Int): Grid = {
    var grid = gridController.grid.value.copy()

    if selectedPos < 0 || elementController.selectedElement.value.isEmpty then return grid
    
    val tiles = grid.elementTiles(elementController.selectedElement.value.get, selectedPos)

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
