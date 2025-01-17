package blockudoku.views.gui

import scalafx.animation.{FadeTransition, ParallelTransition, ScaleTransition, SequentialTransition}
import scalafx.util.Duration
import scalafx.scene.{Node, Scene}
import scalafx.stage
import scalafx.Includes.*
import scalafx.scene.text.Text
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

  def scoreAnimation(text: Text): SequentialTransition  = {


    val scaleTransitionOut = new ScaleTransition {
      node = text
      duration = Duration(300)
      fromX = 1
      fromY = 1
      toX = 1.5
      toY = 1.5
      cycleCount = 1
    }

    val scaleTransitionIn = new ScaleTransition {
      node = text
      duration = Duration(300)
      fromX = 1.5
      fromY = 1.5
      toX = 1
      toY = 1
      cycleCount = 1
    }

    new SequentialTransition {
      children = Seq(scaleTransitionOut, scaleTransitionIn) // insert transitions
    }
  }
}
