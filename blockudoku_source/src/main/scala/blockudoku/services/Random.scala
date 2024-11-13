package blockudoku.services

trait Random {
  def nextInt(upperLimit: Int): Int
  def between(min: Int, max: Int): Int 
}

class RandomImpl extends Random {
  def nextInt(upperLimit: Int): Int = scala.util.Random.nextInt(upperLimit)
  def between(min: Int, max: Int): Int = scala.util.Random.between(min, max)
}