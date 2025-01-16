package test.commands

import test.{StateMatcherSpec, UnitSpec}

class CommandsSpec extends StateMatcherSpec {
  override def onConfigureContainers(): Unit = {
    super.onConfigureContainers()
    includeDefaultConfig()
    includeControllers()
    includeCommands()
  }
}
