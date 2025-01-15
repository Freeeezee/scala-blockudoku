package test

import blockudoku.*
import blockudoku.controllers.GridConfig
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
  
  def includeGridConfig(gridConfig: GridConfig): Unit = {
    container.register[GridConfig](() => gridConfig, Singleton)
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
}
