package blockudoku.services.console

case class ConsoleStyle(code: String)

object ConsoleStyle {
  val BLACK: ConsoleStyle = ConsoleStyle("\u001B[30m")

  val WHITE_BG: ConsoleStyle = ConsoleStyle("\u001B[47m")

  val RESET: ConsoleStyle = ConsoleStyle("\u001B[0m")

  /*def colorized(text: String, consoleColor: ConsoleStyle): String = {
    s"${consoleColor.code}$text${RESET.code}"
  }*/

  def highlighted(text: String): String = {
    s"${WHITE_BG.code}${BLACK.code}$text${RESET.code}"
  }
}