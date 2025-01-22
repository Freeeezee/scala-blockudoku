package blockudoku.saving

/**
 * Manages persistent storage of a string.
 */
trait PersistentStore {

  /**
   * Stores the string in the store.
   * @param string value to store.
   */
  def store(string: String) : Unit

  /**
   * Loads the string from the store.
   * @return the stored string.
   */
  def load(): String
}
