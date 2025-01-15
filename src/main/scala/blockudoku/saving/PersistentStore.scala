package blockudoku.saving

trait PersistentStore {
  
  def store(string: String) : Unit 
  
  def load(): String
}
