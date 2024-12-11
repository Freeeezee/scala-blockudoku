package blockudoku.views.gui

import scala.swing.*

import blockudoku.controllers.ControllerMediator

object GuiLoader extends SimpleSwingApplication {

  // Konstanten für Grid
  val gridsize = 9;
  val cellsize = 30; // verticalFrame
  val gridMargin = 20
  
  def top: Frame = {
    new Frame {
      title="Blockudoku"

      //Headline:
      val heaadline = new Label("Blockudoku") {
        font = new Font("Ariel", java.awt.Font.BOLD, 24)
        horizontalAlignment = Alignment.Center
      }

      //Grid:
      
      val grid = new GridPanel(gridsize, gridsize) {
        preferredSize = new Dimension(gridsize*cellsize, gridsize*cellsize)
        border = Swing.EmptyBorder(gridMargin, gridMargin, gridMargin, gridMargin)
        for (i <- 0 until gridsize; j <- 0 until gridsize) {
          contents += new Button {
            border = Swing.LineBorder(java.awt.Color.GRAY, 1)
            text = ""
            preferredSize = new Dimension(cellsize, cellsize) //?
            background = java.awt.Color.WHITE
          }
        }
      }
      // Buttons:

      val buttons = new FlowPanel {
        contents += new Button("Undo")
        contents += new Button("Redo")
      }

      // Element-Buttons:
      // Elemente:
      val element1, element2, element3 = new GridPanel(3, 3) {
        preferredSize = new Dimension(3*cellsize, 3*cellsize)
        for (i <- 0 until 3; j <- 0 until 3) {
          contents += new Button {
            border = Swing.LineBorder(java.awt.Color.GRAY, 1)
            text = "x"
            preferredSize = new Dimension(cellsize, cellsize)
            background = java.awt.Color.WHITE
          }
        }
      }

      //listenTo(selectElement1, selectElement2, selectElement3)
      listenTo(element1.mouse.clicks)
      reactions += {
        case event.MouseClicked(_, _, _, _, _) => println("Button X1 clicked!")
        //case event.ButtonClicked(`selectElement2`) => println("Button X2 clicked!")
        //case event.ButtonClicked(`selectElement3`) => println("Button X3 clicked!")
      }

      // Überschrift für Elemente
      val elementsLabel = new Label("Elemente") {
        font = new Font("Arial", java.awt.Font.BOLD, 18)
        horizontalAlignment = Alignment.Center
      }

      // Panel für Elemente und Überschrift:
      val elementsPanel = new BorderPanel {
        layout(elementsLabel) = BorderPanel.Position.North
        layout(new FlowPanel { // element1, element2, element3 hinzufügen
          contents ++= Seq(element1, element2, element3)
        }) = BorderPanel.Position.Center
      }

      // Layout:
      contents = new BorderPanel {
        layout(heaadline) = BorderPanel.Position.North
        layout(grid) = BorderPanel.Position.Center
        layout(new BoxPanel(Orientation.Vertical) {
          contents ++= Seq(elementsPanel, buttons)
        }) = BorderPanel.Position.South
      }

      // *contents = new FlowPanel {
        //contents += new Label("Hello, world!")
        //contents += new Button("Click me!") {
          //reactions += {
            //case event.ButtonClicked(_) => println("Button clicked!")
          //}
        //}
      //}
    }
  }
}
// paar views maybe logic (controller)
// publisher: und Reactor -> F. 29
// textfield for Event EditDone
// Button for Event ButtonClicked
// Label for Event MouseClicked?
// Slider for Event ValueChanged


//

