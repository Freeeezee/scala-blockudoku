package test.views

import blockudoku.views.console.ConsoleHeadlineView
import org.junit.Assert.*
import org.junit.Test
import test.UnitSpec

class ConsoleHeadlineViewSpec extends UnitSpec {
  @Test
  def headlineView_widthLessThanHeadlineLengthPlus2_throwsIllegalArgumentException(): Unit = {
    assertThrows(classOf[IllegalArgumentException], () => {
      ConsoleHeadlineView(5)
    })
  }

  @Test
  def headlineView_widthLargeEnough_shouldBeScalable(): Unit = {
    assertEquals("- Blockudoku_ -\n", viewContent(ConsoleHeadlineView(15)))

    assertEquals("--- Blockudoku_ ---\n", viewContent(ConsoleHeadlineView(20)))

    assertEquals("-------- Blockudoku_ --------\n", viewContent(ConsoleHeadlineView(30)))
  }
}
