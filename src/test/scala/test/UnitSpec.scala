package test

import io.gitlab.freeeezee.yadis.ComponentProvider
import org.scalatest.BeforeAndAfterEach
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

abstract class UnitSpec extends AnyWordSpec with Matchers with BeforeAndAfterEach with ContainerConfig {
  
  override def beforeEach(): Unit = {
    super.beforeEach()
    
    configureContainers()
  }
  
  def use(action: ComponentProvider => Unit): Unit = {
    action(provider)
  }
}
