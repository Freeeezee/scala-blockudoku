package blockudoku.saving.serializerJSONImpl

import blockudoku.models.{Colors, Element, Grid, Point, Tile}
import play.api.libs.json.*

object ModelSerializer {

  implicit val pointWrites : OWrites[Point] = Json.writes[Point]
  implicit val pointReads : Reads[Point] = Json.reads[Point]

  implicit val colorWrites: OWrites[Colors] = Json.writes[Colors]
  implicit val colorReads: Reads[Colors] = Json.reads[Colors]

  implicit val elementWrites: OWrites[Element] = Json.writes[Element]
  implicit val elementReads: Reads[Element] = Json.reads[Element]

  implicit val tileWrites: OWrites[Tile] = Json.writes[Tile]
  implicit val tileReads: Reads[Tile] = Json.reads[Tile]

  implicit val gridWrites: OWrites[Grid] = Json.writes[Grid]
  implicit val gridReads: Reads[Grid] = Json.reads[Grid]

}
