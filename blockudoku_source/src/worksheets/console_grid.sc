case class ConsoleColor(colorCode: String)

object ConsoleColor {
  val WHITE = ConsoleColor("\u001B[37m")
  val RED = ConsoleColor("\u001B[31m")
  val GREEN = ConsoleColor("\u001B[32m")
  val BLUE = ConsoleColor("\u001B[34m")
  val RESET = ConsoleColor("\u001B[0m")

  def colorize(text: String, consoleColor: ConsoleColor) : String = {
    s"${consoleColor.colorCode}$text${RESET.colorCode}"
  }
}

case class Tile(content: String, color: ConsoleColor):
  def coloredContent = ConsoleColor.colorize(content, color)

def getHorizontalLine(length: Int) : String = {
  val builder = new StringBuilder

  builder.append('x')
  builder.append("----x" * length)

  builder.result()
}

def draw(x: Int, y: Int)(tiles: Array[Tile]): Unit = {
  for i <- 0 until y do
    println(getHorizontalLine(x))
    for j <- 0 until x do
      print("| ")
      print(tiles(i * x + j).content) // Should be using coloredContent here, but worksheet output makes colored output unreadable
      print(" ")
    print("|\n")
  println(getHorizontalLine(x))
}

val tiles = Array.tabulate(16) { i =>
  val content = f"${i + 1}%02d"
  Tile(content, ConsoleColor.WHITE)
}

draw(4, 4)(tiles)