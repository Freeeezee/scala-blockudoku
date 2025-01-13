package blockudoku.saving

trait Serializer {
  
    // string serialization
    def serialize(): String
    def deserialize(data: String): Unit
}
