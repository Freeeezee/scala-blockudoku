package blockudoku.saving
// savings machen allgemein :)

trait SaveManager {
  
  def save(): Unit 
  def load(): Unit
  
  // speichern und laden
}
