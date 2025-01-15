package blockudoku

import blockudoku.commands.commandsImpl.{CommandFactoryImpl, CommandInvokerImpl}
import blockudoku.commands.{CommandFactory, CommandInvoker}
import blockudoku.controllers.mediatorImpl.{ControllerMediatorImpl, ElementController, ElementControllerImpl, GridController, GridControllerImpl}
import blockudoku.controllers.{ControllerMediator, ElementCollector, GridCollector, GridConfig}
import blockudoku.input.ConsoleInputHandler
import blockudoku.input.consoleInputHandlerImpl.ConsoleInputHandlerImpl
import blockudoku.saving.diskPersistentStoreImpl.PersistentStoreImpl
import blockudoku.saving.{PersistentStore, SaveManager, Serializer}
import blockudoku.saving.saveManagerImpl.SaveManagerImpl
import blockudoku.saving.serializerXMLImpl.SerializerImpl
import blockudoku.services.gridPreviewBuilderImpl.GridPreviewBuilderImpl
import blockudoku.services.{GridPreviewBuilder, Random, RandomImpl}
import blockudoku.views.gui.GuiLoader
import blockudoku.windows.focusManagerImpl.FocusManagerImpl
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
      .registerSaveManager()

    componentContainer
  }
  def registerControllers(): ComponentContainer = {
    componentContainer.register[ElementController, ElementControllerImpl](Singleton)
    componentContainer.register[ElementCollector, ElementControllerImpl](Singleton)
    componentContainer.register[GridCollector, GridControllerImpl](Singleton)
    componentContainer.register[GridController, GridControllerImpl](Singleton)
    componentContainer.register[ControllerMediator, ControllerMediatorImpl](Singleton)
    componentContainer.register[FocusManager, FocusManagerImpl](Singleton)
    componentContainer.register[GridPreviewBuilder, GridPreviewBuilderImpl](Singleton)
    componentContainer.register[GridConfig](()=> GridConfig(), Singleton)
    componentContainer
  }
  def registerTUI(): ComponentContainer = {
    componentContainer.register[ConsoleWindow](Singleton)
    componentContainer.register[ConsoleInputHandler, ConsoleInputHandlerImpl](Singleton)
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
    componentContainer.register[CommandInvoker, CommandInvokerImpl](Singleton)
    componentContainer.register[CommandFactory, CommandFactoryImpl](Singleton)
    componentContainer
  }
  def registerSaveManager(): ComponentContainer = {
    componentContainer.register[SaveManager, SaveManagerImpl](Singleton)
    componentContainer.register[PersistentStore, PersistentStoreImpl](Singleton)
    componentContainer.register[Serializer, SerializerImpl](Singleton)
    componentContainer
  }
}