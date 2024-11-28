package blockudoku.commands

trait Command {
  def execute() : Unit
  def undo() : Unit
}
