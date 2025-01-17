package blockudoku.saving.serializerXMLImpl

import scala.xml.{Elem, Node}

object ScoreSerializer {
  def serialize(score: Int): Elem = {
    <Score>{score}</Score>
  }
  
  def deserialize(data: Node): Int = {
    (data \ "Score").text.toInt
  }
}
