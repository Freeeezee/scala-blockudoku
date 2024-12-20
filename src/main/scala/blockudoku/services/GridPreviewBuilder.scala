package blockudoku.services

import blockudoku.models.Grid

trait GridPreviewBuilder {
  def buildGrid(selectedPos: Int): Grid
}
