package blockudoku.saving.serializerYAMLImpl.deserialize

import scala.collection.mutable
import scala.util.boundary
import scala.util.boundary.break

object YamlParser {
  
  def parse(string: String): Map[String, YamlValue] = {
    val reader = new Reader(string)

    parseReader(reader)
  }

  private def parseReader(reader: Reader, indent: Int = 0): Map[String, YamlValue] = {
    val map = mutable.HashMap[String, YamlValue]()

    boundary:
      while (reader.hasNext) {
        val peekLine = reader.peek
        if (calculateIndent(peekLine.get) < indent) {
          break()
        }

        var line = reader.readLine

        while (line.isEmpty) {
          line = reader.readLine
        }

        val keyValuePair = line.get.split(":")

        val key = keyValuePair(0).trim

        if (keyValuePair.length > 1 && keyValuePair(1).trim.nonEmpty) {
          map.put(key, parseSingleLine(keyValuePair(1).trim))
        } else if (line.get.contains(":")) {
          map.put(key, parseMultiLine(key, reader))
        } else {
          map.put(key, parseSingleLine(line.get.split("-")(1).trim))
        }
      }

    map.toMap
  }

  private def parseSingleLine(string: String): YamlValue = {
    StringYamlValue(string)
  }
  
  private def parseMultiLine(key: String, reader: Reader): YamlValue = {
    val nextLineOption = reader.peek
    if (nextLineOption.isEmpty) {
      throw new RuntimeException("Unexpected end of file")
    }
    val nextLine = nextLineOption.get

    val indent = calculateIndent(nextLine)

    if (nextLine.trim.startsWith("-")) {
      parseArrayContent(reader, indent)
    } else {
      parseObjectContent(reader, indent)
    }
  }

  private def parseArrayContent(reader: Reader, indent: Int): YamlValue = {
    var list = List[YamlValue]()

    boundary:
      while (reader.hasNext) {
        val peekLine = reader.peek.get
        val peekIndent = calculateIndent(peekLine)

        if (peekIndent < indent) {
          break()
        }

        parseReader(reader, indent).foreach(kv => list = list :+ kv._2)
      }

    ArrayYamlValue(list)
  }
  
  private def parseObjectContent(reader: Reader, indent: Int): YamlValue = {
    val map = mutable.HashMap[String, YamlValue]()

    if (reader.peek.isEmpty) {
      throw new RuntimeException("Unexpected end of file")
    }

    boundary:
      while (reader.hasNext) {
        val peekLine = reader.peek.get
        val peekIndent = calculateIndent(peekLine)

        if (peekIndent < indent) {
          break()
        }

        parseReader(reader, indent).foreach(kv => map.put(kv._1, kv._2))
      }

    ObjectYamlValue(map.toMap)
  }

  private def calculateIndent(line: String): Int = {
    line.takeWhile(_.isWhitespace).length
  }
}
  
