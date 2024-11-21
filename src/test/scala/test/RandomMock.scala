package test

import blockudoku.services.Random

class RandomMock extends Random{
   def nextInt(upperLimit: Int): Int = 0
   def between(min: Int, max: Int): Int = min
}
