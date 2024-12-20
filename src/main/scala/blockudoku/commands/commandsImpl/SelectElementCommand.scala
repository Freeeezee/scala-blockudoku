package blockudoku.commands.commandsImpl

import blockudoku.commands.{Command, Snapshotable}
import blockudoku.controllers.ControllerMediator
import blockudoku.models.Element

class SelectElementCommand(element: Element, mediator: ControllerMediator) extends Command(List[Snapshotable[?]](mediator)) {
  override def handleExecute(): Unit = {
    mediator.selectElement(element)
  }
}
