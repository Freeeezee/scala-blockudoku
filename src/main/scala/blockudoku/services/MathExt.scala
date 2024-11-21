package blockudoku.services

object MathExt {
  def clamp(value: Int, min: Int, max: Int): Int = {
    if value < min then min
    else if value > max then max
    else value
  }
}
