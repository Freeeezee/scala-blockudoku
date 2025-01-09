package blockudoku.commands.commandsImpl

import blockudoku.commands.{Command, CommandFactory}
import blockudoku.controllers.ControllerMediator
import blockudoku.models.Element
import com.google.inject.Inject

class CommandFactoryImpl @Inject (mediator: ControllerMediator) extends CommandFactory {
  override def createSelectElementCommand(element: Element): Command = SelectElementCommand(element, mediator)
  override def createSetElementCommand(element: Element, pos: Int): Command = SetElementCommand(element, pos, mediator)
}
