package test

import blockudoku.controllers.mediatorImpl.GridController
import blockudoku.models.{Grid, Point, Tile, TileState}
import io.gitlab.freeeezee.yadis.ComponentProvider

object GridProvider {
  private val defaultSize = 3
  
  def emptyGrid(size: Int = defaultSize)(componentProvider: ComponentProvider): Unit = {
    val grid = generate(List(), size)
    
    load(componentProvider, grid)
  } 
  
  def blockedGrid(componentProvider: ComponentProvider): Unit = {
    val grid = generate(List(0, 1, 2, 3, 4, 5, 6, 7, 8))
    
    load(componentProvider, grid)
  }
  
  def oneRowBlockedGrid(componentProvider: ComponentProvider): Unit = {
    val grid = generate(List(0, 1, 2))
    
    load(componentProvider, grid)
  }
  
  def oneColumnBlockedGrid(componentProvider: ComponentProvider): Unit = {
    val grid = generate(List(0, 3, 6))
    
    load(componentProvider, grid)
  }
  
  private def generate(blocked: List[Int], size: Int = defaultSize): Grid = {
    var tiles = List[Tile]()
    
    for y <- 0 until size do
      for x <- 0 until size do
        val index = y * size + x
        val state = if blocked.contains(index) then TileState.blocked else TileState.empty
        
        val tile = Tile(index, Point(x, y), state = state)
        
        tiles = tiles :+ tile
    
    Grid(size, size, tiles)
  }
  
  private def load(componentProvider: ComponentProvider, grid: Grid): Unit = {
    val gridController = componentProvider.get[GridController]
    
    gridController.loadGrid(grid)
  }
}
