package lectures.beginner.part1basics

object CBNvsCBV extends App {

  // value computed before the call
  def calledByValue(x: Long): Unit = {
    println("by value:" + x)
    println("by value:" + x)
  }

  // => lazy evaluation, x computed 2 times
  def calledByName(x: => Long): Unit = {
    println("by name:" + x)
    println("by name:" + x)
  }

  calledByValue(System.nanoTime())
  calledByName(System.nanoTime())
}
