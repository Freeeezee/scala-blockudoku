package blockudoku.saving

trait SaveManager {
  
  def save(): Unit 
  def load(): Unit
}
