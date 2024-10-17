trait ConsoleElement {
  def content : String
}

case class HorizontalFrame(elements: List[ConsoleElement])(spacing: Int) extends ConsoleElement {
  override def content: String = {
    val elementLines = computedElementLines
    val lineMax = elementLines.map(_.length).max

    val str = new StringBuilder()
    for i <- 0 until lineMax do
        str.append(computedLine(elementLines, i))
        str.append("\n")

    str.result()
  }

  private def computedElementLines : List[Array[String]] = {
    elementContents.map(content => content.split('\n'))
  }
  private def elementContents : List[String] = {
    elements.map(element => element.content)
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
    val maxWidth = lines.map(_.length).max

    if index < lines.length then {
      val padding = " " * (maxWidth - lines(index).length)
      lines(index) + padding
    }
    else " " * maxWidth
  }
}

case class VerticalFrame(elements: List[ConsoleElement])(spacing: Int) extends ConsoleElement {
  override def content: String = {
    val str = new StringBuilder()

    elements.foreach { element =>
      val content = element.content
      str.append(content)
      if !content.endsWith("\n") then str.append("\n")
      str.append(space)
    }

    str.result()
  }

  def space : String = {
    val str = new StringBuilder()

    for i <- 0 until spacing do
      str.append("\n")

    str.result()
  }
}

case class RegularConsoleElement(content: String) extends ConsoleElement

case class ComposedConsoleView(element: ConsoleElement) {
  def display(): Unit = {
    println(element.content)
  }
}

// test composition & output

val content0 = "This is the first test content."
val content1 = "This is a\nmultiline\ncontent."
val content2 = "This is another\nmultiline content."
val content3 = "This\nis the\nlast multiline\ncontent."
val content4 = "This is the last content."

val horizontalFrame = HorizontalFrame(List(RegularConsoleElement(content1), RegularConsoleElement(content2),
  RegularConsoleElement(content3)))(5)

val verticalFrame = VerticalFrame(
  List(RegularConsoleElement(content0), horizontalFrame, RegularConsoleElement(content4)))(2)

val view = ComposedConsoleView(verticalFrame)

view.display()