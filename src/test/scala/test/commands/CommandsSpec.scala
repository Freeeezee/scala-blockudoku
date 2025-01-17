package test.commands

import test.StateMatcherSpec

class CommandsSpec extends StateMatcherSpec {
  override def onConfigureContainers(): Unit = {
    super.onConfigureContainers()
    includeDefaultConfig()
    includeControllers()
    includeCommands()
  }
}
