package blockudoku.commands

import blockudoku.models.{Element, Tile}

trait CommandFactory {
  def createSelectElementCommand(element: Element): Command
  def createSetElementCommand(element: Element, pos: Int): Command
}
