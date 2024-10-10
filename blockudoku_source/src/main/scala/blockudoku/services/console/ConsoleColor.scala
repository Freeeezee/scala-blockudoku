package blockudoku.services.console

case class ConsoleColor(colorCode: String)

object ConsoleColor {
  val WHITE: ConsoleColor = ConsoleColor("\u001B[37m")
  val RED: ConsoleColor = ConsoleColor("\u001B[31m")
  val GREEN: ConsoleColor = ConsoleColor("\u001B[32m")
  val BLUE: ConsoleColor = ConsoleColor("\u001B[34m")
  val RESET: ConsoleColor = ConsoleColor("\u001B[0m")

  def colorize(text: String, consoleColor: ConsoleColor): String = {
    s"${consoleColor.colorCode}$text${RESET.colorCode}"
  }
}