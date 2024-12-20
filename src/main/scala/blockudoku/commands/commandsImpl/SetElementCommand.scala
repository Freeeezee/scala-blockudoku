package blockudoku.commands.commandsImpl

import blockudoku.commands.{Command, Snapshotable}
import blockudoku.controllers.ControllerMediator
import blockudoku.models.Element

class SetElementCommand(element: Element, pos: Int, mediator: ControllerMediator) extends Command(List[Snapshotable[?]](mediator)) {
  override def handleExecute(): Unit = {
    mediator.setElement(element, pos)
  }
}
