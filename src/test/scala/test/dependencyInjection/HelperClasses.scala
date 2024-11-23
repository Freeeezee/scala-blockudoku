package test.dependencyInjection

import scala.util.Random

trait A {
  def getA: String
}

trait B {
  def getB: String
}

class AImpl extends A {
  override def getA: String = "AImpl.doA"
}

class AImpl2 extends A {
  override def getA: String = "AImpl2.doA"
}

class BImpl extends B {
  override def getB: String = "BImpl.doB"
}

class C(a: A, b: B) {
  def getC: String = {
    s"${a.getA} ${b.getB}"
  }
}

class D(c: C) {
  def getD: String = c.getC
}

class E

class ChangingClass {
  val value: Int = Random.nextInt()
}