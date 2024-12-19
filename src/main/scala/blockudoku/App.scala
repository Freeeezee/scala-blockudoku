package blockudoku

import blockudoku.commands.{CommandFactoryImpl, CommandInvoker}
import blockudoku.controllers.{ControllerMediator, ElementControllerImpl, GridControllerImpl}
import blockudoku.services.{ApplicationThread, RandomImpl}
import blockudoku.windows.FocusState.Elements
import blockudoku.windows.*
import io.gitlab.freeeezee.yadis.ComponentContainer

object App {
  
  val container = ComponentContainer().registerComponents().buildProvider()

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
