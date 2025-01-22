package blockudoku.services

import blockudoku.models.Grid

/**
 * Builds a new grid that can be displayed instead of the actual grid, where the currently selected element is placed in the grid.
 */
trait GridPreviewBuilder {
  /**
   * Builds the preview grid with the selected element placed in the grid.
   * @param selectedPos The position to place the element in. 
   * @return The preview grid.
   */
  def buildGrid(selectedPos: Int): Grid
}
