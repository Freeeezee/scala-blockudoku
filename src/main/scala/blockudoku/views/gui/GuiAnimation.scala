package blockudoku.views.gui

import scalafx.animation.FadeTransition
import scalafx.application.JFXApp3
import scalafx.application.JFXApp3.PrimaryStage
import scalafx.util.Duration
import scalafx.scene.{Node, Scene}
import scalafx.stage
import scalafx.Includes._

class GuiAnimation {

  def switchScene(currentSceneRoot: Node, newScene: Scene, oldStage: JFXApp3.PrimaryStage): Unit = {
    val fadeOut = new FadeTransition(Duration(500), currentSceneRoot) {
      fromValue = 1.0
      toValue = 0.0
      onFinished = _ => {
        newScene.root().setOpacity(0)
        oldStage.scene = newScene
        val fadeIn = new FadeTransition(Duration(500), newScene.root()) {
          fromValue = 0.0
          toValue = 1.0
        }
        fadeIn.play()
      }
    }
    fadeOut.play()
  }

  // def fadeOutBlocks oder so wenn block reihe gesetzt wurde oder partikel
}
