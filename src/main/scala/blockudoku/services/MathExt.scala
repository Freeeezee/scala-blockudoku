package blockudoku.services

/**
 * Contains math-related utility functions.
 */
object MathExt {
  /**
   * Clamps a value between a minimum and a maximum.
   * @param value value to clamp.
   * @param min minimum value.
   * @param max maximum value.
   * @return clamped value.
   */
  def clamp(value: Int, min: Int, max: Int): Int = {
    if value < min then min
    else if value > max then max
    else value
  }
}
