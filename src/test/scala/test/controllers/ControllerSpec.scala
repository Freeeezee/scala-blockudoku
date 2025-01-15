package test.controllers

import test.UnitSpec

class ControllerSpec extends UnitSpec {
  override def onConfigureContainers(): Unit = {
    super.onConfigureContainers()
    includeDefaultConfig()
    includeControllers()
  }
}
