package test.controllers

import blockudoku.controllers.ScoreController
import test.GridProvider

class ScoreControllerSpec extends ControllerSpec {
  "A ScoreController" when {
    "testGridState is called on a grid without full sections" should {
      "return an empty set and not increase the score" in {
        use(GridProvider.emptyGrid())

        val controller = provider.get[ScoreController]
        val previousScore = controller.getScore

        val result = controller.testGridState()

        result shouldBe empty
        controller.getScore shouldBe previousScore
      }
    }

    "testGridState is called on a grid with a full row" should {
      "return a set with the row and increase the score" in {
        use(GridProvider.oneRowBlockedGrid)

        val controller = provider.get[ScoreController]
        val previousScore = controller.getScore

        val result = controller.testGridState()

        result.size shouldBe > (0)
        controller.getScore shouldBe > (previousScore)
      }
    }

    "testGridState is called on a grid with a full column" should {
      "return a set with the column and increase the score" in {
        use(GridProvider.oneColumnBlockedGrid)

        val controller = provider.get[ScoreController]
        val previousScore = controller.getScore

        val result = controller.testGridState()

        result.size shouldBe > (0)
        controller.getScore shouldBe > (previousScore)
      }
    }
  }
}
