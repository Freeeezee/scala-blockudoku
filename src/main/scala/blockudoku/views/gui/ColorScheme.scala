package blockudoku.views.gui

import blockudoku.observer.Observable

object ColorScheme extends Observable {
  
  private val colorSchemes : List[List[String]] = List(
    List(
      "/block_purple1.png",
      "/block_purple2.png",
      "/block_purple3.png",
      "/block_purple4.png",
      "/block_purple5.png"),
    List(
      "/block_blue1.png",
      "/block_blue2.png",
      "/block_blue3.png",
      "/block_blue4.png",
      "/block_blue5.png"),
    List(
      "/block_green1.png",
      "/block_green2.png",
      "/block_green3.png",
      "/block_green4.png",
      "/block_green5.png"),
    List(
      "/block_red1.png",
      "/block_red2.png",
      "/block_red3.png",
      "/block_red4.png",
      "/block_red5.png"),
  )

  var current: List[String] = colorSchemes.head
  
  def setColorScheme(index: Int): Unit = {
    current = colorSchemes(index)
    notifyObservers()
  }
  def getColor(index: Int): String = {
    current(index)
  }
}
