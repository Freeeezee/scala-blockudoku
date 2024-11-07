package blockudoku.views.console.composed

import blockudoku.services.console.ConsoleStyle.highlighted

case class HorizontalFrame(elements: List[ConsoleElement])(spacing: Int, override val isInteractable: Boolean = false) extends Frame(elements) {
  override def content(highlightIndex: Int): String = {
    val elementLines = computedElementLines(highlightIndex)
    val lineMax = elementLines.map(_.length).max

    val str = new StringBuilder()
    for i <- 0 until lineMax do
      str.append(computedLine(elementLines, i))
      if i < lineMax - 1 then str.append("\n")

    if highlightIndex == size then highlighted(str.result())
    else str.result()
  }

  private def computedElementLines(highlightIndex: Int) : List[Array[String]] = {
    elementContents(highlightIndex).map(content => content.split('\n'))
  }
  private def elementContents(highlightIndex: Int) : List[String] = {
    var contents = List[String]()
    var traversedSize: Int = 0

    for i <- elements.indices do
      val content = elements(i).content(highlightIndex - traversedSize)
      contents = contents :+ content
      traversedSize += elements(i).size

    contents
  }
  private def computedLine(elementLines: List[Array[String]], lineIndex: Int) : String = {
    val str = new StringBuilder()

    elementLines.foreach { element =>
      str.append(computedElementContent(element)(lineIndex))
      str.append(" " * spacing)
    }

    str.result()
  }
  private def computedElementContent(lines: Array[String])(index: Int): String = {
    val maxWidth = lines.map(stripAnsiCodes(_).length).max

    if index < lines.length then {
      val padding = " " * (maxWidth - stripAnsiCodes(lines(index)).length)
      lines(index) + padding
    }
    else " " * maxWidth
  }

  private def stripAnsiCodes(text: String): String = {
    val ansiPattern = "\u001B\\[[;\\d]*m".r
    ansiPattern.replaceAllIn(text, "")
  }

  override def interactableIndices(currentIndex: Int = 1): List[List[Int]] = {
    if !isInteractable then return List[List[Int]]()

    var interactableIndices = List[List[Int]]()

    var index = currentIndex

    val elementInteractableIndices = elements.map { element =>
      val indices = element.interactableIndices(index)
      index = index + element.size
      indices
    }

    for i <- 0 until elementInteractableIndices.map(outerList => outerList.length).max do
      val row = elementInteractableIndices.flatMap { outerList =>
        if i < outerList.length then outerList(i) else List()
      }
      interactableIndices = interactableIndices :+ row

    interactableIndices
  }
}