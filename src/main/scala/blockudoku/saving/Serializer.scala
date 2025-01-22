package blockudoku.saving

/**
 * Handles serialization and deserialization of the game state.
 */
trait Serializer {

    /**
     * Serializes the game state.
     * @return the serialized game state as a string.
     */
    def serialize(): String

    /**
     * Deserializes the game state.
     * @param data the serialized game state as a string.
     */
    def deserialize(data: String): Unit
}
