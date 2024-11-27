package blockudoku.windows

class TestWindow extends Window {
  
  var stopp: Boolean = false
  
  def display(): Unit = {
    
    if !stopp then println("This is a test :)")
    stopp = true
  }

  def anyChange(): Boolean = {
    false
  }

  def handleInput(): Unit = {
    
  }
}
