package blockudoku.commands

import blockudoku.controllers.ControllerMediator
import blockudoku.models.Element

class CommandFactoryImpl(mediator: ControllerMediator) extends CommandFactory {
  override def createSelectElementCommand(element: Element): Command = SelectElementCommand(element, mediator)
  override def createSetElementCommand(element: Element, pos: Int): Command = SetElementCommand(element, pos, mediator)
}
