package blockudoku.commands

trait Command(snapshotables : List[Snapshotable[?]]) {
  def execute() : Unit = {
    snapshotables.foreach(_.createSnapshot())
    handleExecute()
  }
  def undo() : Unit = {
    snapshotables.foreach(_.revertSnapshot())
  }
  def handleExecute() : Unit
}
