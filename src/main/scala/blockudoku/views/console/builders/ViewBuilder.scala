package blockudoku.views.console.builders

import blockudoku.views.console.ConsoleView

trait ViewBuilder {
  def build(): List[ConsoleView]
}
