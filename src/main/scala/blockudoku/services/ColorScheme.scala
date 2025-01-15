package blockudoku.services

object ColorScheme {
  
  val colorSchemes : List[List[String]] = List(
    List(
      "file:src/main/resources/block_purple1.png",
      "file:src/main/resources/block_purple2.png",
      "file:src/main/resources/block_purple3.png",
      "file:src/main/resources/block_purple4.png",
      "file:src/main/resources/block_purple5.png"),
    List(
      "file:src/main/resources/block_blue1.png",
      "file:src/main/resources/block_blue2.png",
      "file:src/main/resources/block_blue3.png",
      "file:src/main/resources/block_blue4.png",
      "file:src/main/resources/block_blue5.png"),
    List(
      "file:src/main/resources/block_green1.png",
      "file:src/main/resources/block_green2.png",
      "file:src/main/resources/block_green3.png",
      "file:src/main/resources/block_green4.png",
      "file:src/main/resources/block_green5.png"),
    List(
      "file:src/main/resources/block_red1.png",
      "file:src/main/resources/block_red2.png",
      "file:src/main/resources/block_red3.png",
      "file:src/main/resources/block_red4.png",
      "file:src/main/resources/block_red5.png"),
  )

  var current: List[String] = colorSchemes.head
  
  def setColorScheme(index: Int): Unit = {
    current = colorSchemes(index)
  }
  def getColor(index: Int): String = {
    current(index)
  }
  def getRandomColor: Int = {
    scala.util.Random.nextInt(current.length)
  }
}
