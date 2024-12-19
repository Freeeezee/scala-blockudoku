package blockudoku

import blockudoku.commands.{CommandFactory, CommandFactoryImpl, CommandInvoker}
import blockudoku.controllers.{ControllerMediator, ElementController, ElementControllerImpl, GridController, GridControllerImpl}
import blockudoku.input.ConsoleInputHandler
import blockudoku.models.{ElementCollector, GridCollector}
import blockudoku.services.{GridPreviewBuilder, Random, RandomImpl}
import blockudoku.views.gui.GuiLoader
import blockudoku.windows.{ConsoleWindow, FocusManager, GuiWindow}
import io.gitlab.freeeezee.yadis.ComponentContainer
import io.gitlab.freeeezee.yadis.Lifetime.Singleton

extension (componentContainer: ComponentContainer) {

  def registerComponents(): ComponentContainer = {
    componentContainer
      .registerControllers()
      .registerTUI()
      .registerGUI()
      .registerRandom()
      .registerCommands()
    
    componentContainer
  }
  def registerControllers(): ComponentContainer = {
    componentContainer.register[ElementController, ElementControllerImpl](Singleton)
    componentContainer.register[ElementCollector, ElementControllerImpl](Singleton)
    componentContainer.register[GridCollector, GridControllerImpl](Singleton)
    componentContainer.register[GridController, GridControllerImpl](Singleton)
    componentContainer.register[ControllerMediator](Singleton)
    componentContainer.register[FocusManager](Singleton)
    componentContainer.register[GridPreviewBuilder](Singleton)
    componentContainer
  }
  def registerTUI(): ComponentContainer = {
    componentContainer.register[ConsoleWindow](Singleton)
    componentContainer.register[ConsoleInputHandler](Singleton)
    componentContainer
  }
  def registerGUI(): ComponentContainer = {
    componentContainer.register[GuiLoader](Singleton)
    componentContainer.register[GuiWindow](Singleton)
    componentContainer
  }
  def registerRandom(): ComponentContainer = {
    componentContainer.register[Random, RandomImpl](Singleton)
    componentContainer
  }
  def registerCommands(): ComponentContainer = {
    componentContainer.register[CommandInvoker](Singleton)
    componentContainer.register[CommandFactory, CommandFactoryImpl](Singleton)
    componentContainer
  }
}