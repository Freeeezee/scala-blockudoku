package blockudoku.saving.serializerYAMLImpl.deserialize

import java.io.{BufferedReader, StringReader}
import scala.util.boundary.break

object YamlParser {
  
  def parse(string: String): Unit = {
    var pairs = List[KeyValuePair]()
    
    val reader = new Reader(string)
    while (reader.hasNext) {
      val line = reader.readLine
      val keyValuePair = line.get.split(":")
      if (keyValuePair(1).trim.nonEmpty) {
        pairs = KeyValuePair(keyValuePair(0).trim, keyValuePair(1).trim, false) :: pairs
      } else {
        pairs = parseMultiLine(keyValuePair(0).trim, reader) :: pairs
      }
    }
  }
  
  private def parseMultiLine(key: String, reader: Reader): KeyValuePair = {
    val line = reader.readLine
    if (line.isEmpty) {
      return KeyValuePair(key, "", false)
    }
    val value = new StringBuilder
    val indent = line.get.takeWhile(_.isWhitespace).length
    value.append(line.get.trim)
    readContent(reader, value, indent)
    KeyValuePair(key, value.toString, true)
  }
  
  private def readContent(reader: Reader, value : StringBuilder, indent: Int): Unit = {
    while (reader.hasNext) {
      val peekLine = reader.peek
      if (peekLine.isEmpty) then return
      val peekIndent = peekLine.get.takeWhile(_.isWhitespace).length
      if (peekIndent < indent) then return
      value.append("\n")
      value.append(peekLine.get.trim)
      reader.readLine
    }
  }
}
  
