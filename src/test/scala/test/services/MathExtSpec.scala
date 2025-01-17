package test.services

import blockudoku.services.MathExt
import test.UnitSpec

class MathExtSpec extends UnitSpec {
  "MathExt" when {
    "clamp is called" should {
      "make sure the value is between the given range" in {
        MathExt.clamp(5, 0, 10) shouldBe 5
        MathExt.clamp(-5, 0, 10) shouldBe 0
        MathExt.clamp(15, 0, 10) shouldBe 10
      }
    }
  }
}
