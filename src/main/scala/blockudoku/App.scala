package blockudoku

import blockudoku.controllers.mediatorImpl.ScoreController
import blockudoku.services.ApplicationThread
import blockudoku.windows.*
import io.gitlab.freeeezee.yadis.ComponentContainer

/**
 * Main application runner.
 */
object App {
  
  private val container = ComponentContainer().registerComponents().buildProvider()

  /**
   * Runs the application.
   */
  def run(): Unit = {
    
    val guiWindow = container.get[GuiWindow]
    val consoleWindow = container.get[ConsoleWindow]
    val scoreController = container.get[ScoreController]

    ApplicationThread().run {
      guiWindow.display()
    }
    
    consoleWindow.display()
  }

  /**
   * Exits the application.
   */
  def exit(): Unit = {
    System.exit(0)
  }
}
