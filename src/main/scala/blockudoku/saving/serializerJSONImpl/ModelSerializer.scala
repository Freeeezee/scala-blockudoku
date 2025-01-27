package blockudoku.saving.serializerJSONImpl

import blockudoku.models.{Element, Grid, Point, Tile, TileState}
import play.api.libs.json.*

object ModelSerializer {

  implicit val pointWrites : OWrites[Point] = Json.writes[Point]
  implicit val pointReads : Reads[Point] = Json.reads[Point]

  implicit val elementWrites: OWrites[Element] = Json.writes[Element]
  implicit val elementReads: Reads[Element] = Json.reads[Element]

  implicit val tileStateWrites: OWrites[TileState] = (tileState: TileState) => Json.obj("state" -> tileState.toString)
  implicit val tileStateReads : Reads[TileState] = (json: JsValue) => JsSuccess(TileState.valueOf((json\"state").as[String]))

  implicit val tileWrites: OWrites[Tile] = Json.writes[Tile]
  implicit val tileReads: Reads[Tile] = Json.reads[Tile]

  implicit val gridWrites: OWrites[Grid] = Json.writes[Grid]
  implicit val gridReads: Reads[Grid] = Json.reads[Grid]

}
