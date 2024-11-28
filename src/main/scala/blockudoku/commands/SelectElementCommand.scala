package blockudoku.commands

import blockudoku.controllers.ControllerMediator
import blockudoku.models.Element

class SelectElementCommand(element: Element, mediator: ControllerMediator) extends Command {
  override def execute(): Unit = {
    mediator.createSnapshot()
    mediator.selectElement(element)
  }

  override def undo(): Unit = ???

}
