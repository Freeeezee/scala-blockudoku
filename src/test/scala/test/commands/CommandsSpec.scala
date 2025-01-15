package test.commands

import test.UnitSpec

class CommandsSpec extends UnitSpec {
  override def onConfigureContainers(): Unit = {
    super.onConfigureContainers()
    includeDefaultConfig()
    includeControllers()
    includeCommands()
  }
}
