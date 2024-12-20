package blockudoku

import blockudoku.commands.CommandInvoker
import blockudoku.commands.commandsImpl.CommandFactoryImpl
import blockudoku.controllers.mediatorImpl.{ElementControllerImpl, GridControllerImpl}
import blockudoku.controllers.ControllerMediator
import blockudoku.services.{ApplicationThread, RandomImpl}
import blockudoku.windows.*
import io.gitlab.freeeezee.yadis.ComponentContainer

object App {
  
  private val container = ComponentContainer().registerComponents().buildProvider()

  def run(): Unit = {
    
    val guiWindow = container.get[GuiWindow]
    val consoleWindow = container.get[ConsoleWindow]

    ApplicationThread().run {
      guiWindow.display()
    }
    
    consoleWindow.display()
  }
  
  def exit(): Unit = {
    System.exit(0)
  }
}
