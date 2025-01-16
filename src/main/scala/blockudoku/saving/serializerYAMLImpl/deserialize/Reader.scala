package blockudoku.saving.serializerYAMLImpl.deserialize

import java.io.{BufferedReader, StringReader}

class Reader(string: String) {
  
  private var nextLine: Option[String] = None

  val reader = new BufferedReader(new StringReader(string))
  
  readLine
  
  def hasNext: Boolean = {
    nextLine.isDefined
  }
  
  def peek: Option[String] = {
    nextLine
  }
  
  def readLine: Option[String] = {
    val line = nextLine
    if (reader.ready) {
      nextLine = Some(reader.readLine())
    }
    line
  }
}
