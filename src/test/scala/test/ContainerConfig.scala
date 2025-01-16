package test

import blockudoku.*
import blockudoku.controllers.GridConfig
import blockudoku.saving.diskPersistentStoreImpl.PersistentStoreImpl
import blockudoku.saving.{PersistentStore, SaveManager, Serializer}
import blockudoku.saving.saveManagerImpl.SaveManagerImpl
import blockudoku.services.Random
import io.gitlab.freeeezee.yadis.Lifetime.Singleton
import io.gitlab.freeeezee.yadis.{ComponentContainer, ComponentProvider}

import scala.compiletime.uninitialized

trait ContainerConfig {
  var container: ComponentContainer = uninitialized
  var provider: ComponentProvider = uninitialized

  def onConfigureContainers(): Unit = {}

  def configureContainers(): Unit = {
    container = ComponentContainer()
    
    container.register[Random, RandomMock](Singleton)

    onConfigureContainers()

    provider = container.buildProvider()
  }
  
  def includeDefaultConfig(): Unit = {
    container.registerConfig()
  }
  
  def includeControllers(): Unit = {
    container.registerControllers()
  }
  
  def includeCommands(): Unit = {
    container.registerCommands()
  }
  
  def includeSaveManager(): Unit = {
    container.registerSaveManager()
  }
  
  def includeSaveManagerWithoutSerializer(): Unit = {
    container.register[SaveManager, SaveManagerImpl](Singleton)
    container.register[PersistentStore, PersistentStoreImpl](Singleton)
  }
  
  def includeXmlSerializer(): Unit = {
    container.register[Serializer, blockudoku.saving.serializerXMLImpl.SerializerImpl](Singleton)
  }
  
  def includeJsonSerializer(): Unit = {
    container.register[Serializer, blockudoku.saving.serializerJSONImpl.SerializerImpl](Singleton)
  }
}
