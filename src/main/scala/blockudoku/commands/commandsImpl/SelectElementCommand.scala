package blockudoku.commands.commandsImpl

import blockudoku.commands.{Command, Snapshotable}
import blockudoku.controllers.ControllerMediator
import blockudoku.models.Element
import com.google.inject.Inject

class SelectElementCommand @Inject (element: Element, mediator: ControllerMediator) extends Command(List[Snapshotable[?]](mediator)) {
  override def handleExecute(): Unit = {
    mediator.selectElement(element)
  }
}
