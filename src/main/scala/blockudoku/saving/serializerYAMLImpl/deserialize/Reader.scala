package blockudoku.saving.serializerYAMLImpl.deserialize

class Reader(string: String) {
  private val lines = string.split("\n")
  private var index = 0
  private var nextLine: Option[String] = Some(lines(index))

  def hasNext: Boolean = {
    nextLine.isDefined
  }
  
  def peek: Option[String] = {
    nextLine
  }
  
  def readLine: Option[String] = {
    val line = nextLine

    if (index < lines.length) {
      nextLine = Some(lines(index))
      index += 1
    } else {
      nextLine = None
    }

    line
  }
}
