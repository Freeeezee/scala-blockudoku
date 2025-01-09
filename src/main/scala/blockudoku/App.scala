package blockudoku

import blockudoku.commands.CommandInvoker
import blockudoku.commands.commandsImpl.CommandFactoryImpl
import blockudoku.controllers.mediatorImpl.{ElementControllerImpl, GridControllerImpl}
import blockudoku.controllers.ControllerMediator
import blockudoku.services.{ApplicationThread, RandomImpl}
import blockudoku.windows.*
import com.google.inject.{Guice, Injector}
import io.gitlab.freeeezee.yadis.ComponentContainer

object App {
  
  private val injector = Guice.createInjector(new BlockudokuModule) // Injector for dependency injection
  
  //private val container = ComponentContainer().registerComponents().buildProvider()

  def run(): Unit = {

    val guiWindow = injector.getInstance(classOf[GuiWindow])
    val consoleWindow = injector.getInstance(classOf[ConsoleWindow])
    //val guiWindow = container.get[GuiWindow]
    //val consoleWindow = container.get[ConsoleWindow]

    ApplicationThread().run {
      guiWindow.display()
    }
    
    consoleWindow.display()
  }
  
  def exit(): Unit = {
    System.exit(0)
  }
}
