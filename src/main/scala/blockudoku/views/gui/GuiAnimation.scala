package blockudoku.views.gui

import scalafx.animation.FadeTransition
import scalafx.application.JFXApp3
import scalafx.application.JFXApp3.PrimaryStage
import scalafx.util.Duration
import scalafx.scene.{Node, Scene}
import scalafx.stage
import scalafx.Includes.*
import scalafx.stage.Stage

class GuiAnimation {

  def switchScene(stage: Stage, oldScene: Scene, newScene: Scene): Unit = {
    if (oldScene != null && oldScene.root() != null) {
      val fadeOut = new FadeTransition(Duration(300), oldScene.root()) {
        fromValue = 1.0
        toValue = 0.0
        onFinished = _ => {
          stage.scene = newScene
          val fadeIn = new FadeTransition(Duration(300), newScene.root()) {
            fromValue = 0.0
            toValue = 1.0
          }
          fadeIn.play()
        }
      }
      fadeOut.play()
    } else {
      stage.scene = newScene
      val fadeIn = new FadeTransition(Duration(300), newScene.root()) {
        fromValue = 0.0
        toValue = 1.0
      }
      fadeIn.play()
    }
  }

  def fadeOutBlocks(blocks: Seq[Node]): Unit = {
    blocks.foreach { block =>
      val fadeOut = new FadeTransition(Duration(300), block) {
        fromValue = 1.0
        toValue = 0.0
      }
      fadeOut.play()
    }
  }
}
  // def fadeOutBlocks oder so wenn block reihe gesetzt wurde oder partikel
