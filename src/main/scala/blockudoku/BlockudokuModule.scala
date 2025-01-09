package blockudoku

import com.google.inject.{AbstractModule, Inject, Provider, Scopes}
import net.codingwell.scalaguice.ScalaModule
import blockudoku.commands.commandsImpl.{CommandFactoryImpl, CommandInvokerImpl}
import blockudoku.commands.{CommandFactory, CommandInvoker}
import blockudoku.controllers.mediatorImpl.{ControllerMediatorImpl, ElementController, ElementControllerImpl, GridController, GridControllerImpl}
import blockudoku.controllers.{ControllerMediator, ElementCollector, GridCollector, GridConfig}
import blockudoku.input.ConsoleInputHandler
import blockudoku.input.consoleInputHandlerImpl.ConsoleInputHandlerImpl
import blockudoku.services.gridPreviewBuilderImpl.GridPreviewBuilderImpl
import blockudoku.services.{GridPreviewBuilder, Random, RandomImpl}
import blockudoku.views.gui.GuiLoader
import blockudoku.windows.focusManagerImpl.FocusManagerImpl
import blockudoku.windows.{ConsoleWindow, FocusManager, GuiWindow}


// Bindings for dependency injection
class BlockudokuModule extends SingletonModule { // with ScalaModule

  override def configure(): Unit = {

    // Controller:
    bindSingleTon(classOf[GridControllerImpl])

    bind(classOf[GridController]).toProvider(classOf[GridControllerProvider])
    bind(classOf[GridCollector]).toProvider(classOf[GridCollectorProvider])

    bindSingleTon(classOf[ElementControllerImpl])

    bind(classOf[ElementController]).toProvider(classOf[ElementControllerProvider])
    bind(classOf[ElementCollector]).toProvider(classOf[ElementCollectorProvider])

    bindSingleton(classOf[ControllerMediator], classOf[ControllerMediatorImpl])
    bindSingleton(classOf[FocusManager], classOf[FocusManagerImpl])
    bindSingleton(classOf[GridPreviewBuilder], classOf[GridPreviewBuilderImpl])
    bind(classOf[GridConfig]).toInstance(GridConfig())

    // TUI:
    // TUI bindings
    bindSingleTon(classOf[ConsoleWindow])
    bindSingleton(classOf[ConsoleInputHandler], classOf[ConsoleInputHandlerImpl])

    // GUI bindings
    bindSingleTon(classOf[GuiLoader])
    bindSingleTon(classOf[GuiWindow])

    // Random bindings
    bindSingleton(classOf[Random], classOf[RandomImpl])

    // Command bindings
    bindSingleton(classOf[CommandInvoker], classOf[CommandInvokerImpl])
    bindSingleton(classOf[CommandFactory], classOf[CommandFactoryImpl])
  }
}
class GridControllerProvider extends Provider[GridController] {
  @Inject var gridController: GridControllerImpl = _
  override def get(): GridController = gridController
}

class GridCollectorProvider extends Provider[GridCollector] {
  @Inject var gridCollector: GridControllerImpl = _
  override def get(): GridCollector = gridCollector
}

class ElementControllerProvider extends Provider[ElementController] {
  @Inject var elementController: ElementControllerImpl = _
  override def get(): ElementController = elementController
}

class ElementCollectorProvider extends Provider[ElementCollector] {
  @Inject var elementCollector: ElementControllerImpl = _
  override def get(): ElementCollector = elementCollector
}


// Windows mit Annotation?
// für GridPreviewBuilderImpl annotation ? (collecotr)
// für Frames? -> Console Element
// ConsoleViews? / GuiViews?

/* bind(classOf[Service]).to(classOf[ServiceImpl]).in(Scopes.SINGLETON)
oder:
 @Singleton
class ServiceImpl extends Service
_____________________________________________________
bind(classOf[Service]).to(classOf[ServiceImpl])
-> Singleton: gleiche Instanz immer wieder verwendet

_____________________________________________________
bind(classOf[Config]).toInstance(new Config("localhost", 8080))
-> für spezifische Instanz; Guice gibt angegebene Instanz (new Config("localhost", 8080)) zurück

_____________________________________________________
class ServiceProvider extends Provider[Service] {
  override def get(): Service = new ServiceImpl("config-dependent")
}

bind(classOf[Service]).toProvider(classOf[ServiceProvider])
-> Provider: Guice ruft get() auf, um mit dieser Logik Instanz zu erstellen
_____________________________________________________

MIT FACTORY VERBINDEN:

trait MyFactory {
  def create(param: String): MyService
}

bind(classOf[MyFactory]).toFactory()
-> Factory: Guice ruft create() auf, um Instanz zu erstellen?

_____________________________________________________

bind[GridInterface].annotatedWithName("tiny").toInstance(new Grid(1))
-> Annotation: spezifische Instanz für spezifische Annotation
*/
