import scala.:+

def highlighted(string: String): String = {
  val whiteBackground = "\u001B[47m"
  val blackText = "\u001B[30m"
  val reset = "\u001B[0m"
  s"$whiteBackground$blackText$string$reset"
}

trait ConsoleElement {
  val isInteractable: Boolean = false
  val size: Int
  def content(highlightIndex: Int) : String
}

trait Frame(elements: List[ConsoleElement]) extends ConsoleElement {
  override val size: Int = calculateSize

  private def calculateSize: Int = {
    var size = 1

    elements.foreach { element =>
      size = size + element.size
    }

    size
  }
}

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
    val maxWidth = lines.map(_.length).max

    if index < lines.length then {
      val padding = " " * (maxWidth - lines(index).length)
      lines(index) + padding
    }
    else " " * maxWidth
  }
}

case class VerticalFrame(elements: List[ConsoleElement])(spacing: Int, override val isInteractable: Boolean = false) extends Frame(elements) {
  override def content(highlightIndex: Int): String = {
    val str = new StringBuilder()

    contents(highlightIndex).foreach { content =>
      str.append(content)
      str.append(space)
    }

    if highlightIndex == size then highlighted(str.result())
    else str.result()
  }

  private def contents(highlightIndex: Int): List[String] = {
    var contents = List[String]()
    var traversedSize: Int = 0

    for i <- elements.indices do
      val content = elements(i).content(highlightIndex - traversedSize)
      contents = contents :+ content
      traversedSize += elements(i).size

    contents
  }

  private def space : String = {
    val str = new StringBuilder()

    for i <- 0 until spacing do
      str.append("\n")

    str.result()
  }
}

case class RegularConsoleElement(content: String, override val isInteractable: Boolean = false) extends ConsoleElement {
  override val size: Int = 1

  override def content(highlightIndex: Int): String = {
    if highlightIndex == size then highlighted(content)
    else content
  }
}

case class ComposedConsoleView(element: ConsoleElement, highlightedIndex: Int = 1) {
  def display(): Unit = {
    println(element.content(highlightedIndex))
  }
}

// test composition & output

val content0 = "This is the first test content."
val content1 = "This is a\nmultiline\ncontent."
val content2 = "This is another\nmultiline content."
val content3 = "This\nis the\nlast multiline\ncontent."
val content4 = "This is the last content."

val horizontalFrame = HorizontalFrame(List(
  RegularConsoleElement(content1, isInteractable = true),
  RegularConsoleElement(content2, isInteractable = true),
  RegularConsoleElement(content3, isInteractable = true)))(5, isInteractable = true)

val verticalFrame = VerticalFrame(List(
  RegularConsoleElement(content0),
  horizontalFrame,
  RegularConsoleElement(content4)))(2, isInteractable = true)

val view = ComposedConsoleView(verticalFrame, highlightedIndex = 1)

view.display()