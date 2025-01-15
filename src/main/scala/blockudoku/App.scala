package blockudoku

import blockudoku.controllers.ScoreController
import blockudoku.services.ApplicationThread
import blockudoku.windows.*
import io.gitlab.freeeezee.yadis.ComponentContainer

object App {
  
  private val container = ComponentContainer().registerComponents().buildProvider()

  def run(): Unit = {
    
    val guiWindow = container.get[GuiWindow]
    val consoleWindow = container.get[ConsoleWindow]
    val scoreController = container.get[ScoreController]

    ApplicationThread().run {
      guiWindow.display()
    }
    
    consoleWindow.display()
  }
  
  def exit(): Unit = {
    System.exit(0)
  }
}
