package blockudoku.services

/**
 * Abstracts random number generation to allow for testing.
 */
trait Random {
  /**
   * Generates a random integer between 0 and upperLimit.
   * @param upperLimit the maximum generated value.
   * @return the generated integer.
   */
  def nextInt(upperLimit: Int): Int

  /**
   * Generates a random integer between min and max.
   * @param min the minimum generated value.
   * @param max the maximum generated value.
   * @return the generated integer.
   */
  def between(min: Int, max: Int): Int 
}

class RandomImpl extends Random {
  def nextInt(upperLimit: Int): Int = scala.util.Random.nextInt(upperLimit)
  def between(min: Int, max: Int): Int = scala.util.Random.between(min, max)
}