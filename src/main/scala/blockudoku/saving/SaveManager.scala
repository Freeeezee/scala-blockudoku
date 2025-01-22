package blockudoku.saving

/**
 * Handles saving and loading of the game state.
 */
trait SaveManager {
  
  /**
   * Saves the current game state.
   */
  def save(): Unit 
  
  /**
   * Loads the game state.
   */
  def load(): Unit
}
