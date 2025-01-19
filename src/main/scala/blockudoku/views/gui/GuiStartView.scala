package blockudoku.views.gui

import blockudoku.saving.SaveManager
import scalafx.animation.AnimationTimer
import scalafx.geometry.Pos
import scalafx.scene.Node
import scalafx.scene.image.Image
import scalafx.scene.layout.*
import scalafx.scene.text.{Font, Text, TextAlignment}

class GuiStartView(guiLoader: GuiLoader, saveManager: SaveManager) extends GuiView {
  private var currentBackgroundIndex = 0
  private val frameDelay = 500000000L // 1 sec in nanoseconds
  private var lastUpdate = 0L

  override def element: Node = {
    val backgroundImages = (1 to 11).map { i =>
      new Image(getClass.getResourceAsStream(s"/background_$i.png"))
    }

    val root = new VBox {
      alignment = Pos.Center
      spacing = 10
      prefWidth <== guiLoader.stage.width
      prefHeight <== guiLoader.stage.height

      children = Seq(
        new Text {
          text = "Welcome to Blockudoku!"
          font = Font.loadFont(getClass.getResourceAsStream("/Audiowide-Regular.ttf"), 50)
          textAlignment = TextAlignment.Center
          wrappingWidth <== guiLoader.stage.width * 0.75
        },
        new VBox {
          alignment = Pos.Center
          spacing = 10
          children = Seq(
            new GuiButton("Start", _ => {
              guiLoader.switchToScene(guiLoader.mainScene)
            }),
            new GuiButton("Settings", _ => {
              guiLoader.switchToScene(guiLoader.settingsScene)
            }),
            new GuiButton("Load", _ => {
              guiLoader.switchToScene(guiLoader.mainScene)
              saveManager.load()
            })
          )
        }
      )
    }

    val timer = AnimationTimer { now =>
      if (now - lastUpdate >= frameDelay) {
        currentBackgroundIndex = (currentBackgroundIndex + 1) % backgroundImages.length
        root.background = new Background(Array(new BackgroundImage(
          backgroundImages(currentBackgroundIndex),
          BackgroundRepeat.NoRepeat,
          BackgroundRepeat.NoRepeat,
          BackgroundPosition.Center,
          new BackgroundSize(BackgroundSize.Auto, BackgroundSize.Auto, false, false, true, false)
        )))
        lastUpdate = now
      }
    }
    timer.start()

    root
  }
}