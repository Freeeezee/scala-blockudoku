package blockudoku.saving

trait Serializer {
  
    def serialize(): String
    def deserialize(data: String): Unit
}
