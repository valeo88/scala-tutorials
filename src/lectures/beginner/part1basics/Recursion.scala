package lectures.beginner.part1basics

import scala.annotation.tailrec

object Recursion extends App {

  // has limitation due to stack size
  def factorial(n: Int): Int = {
    if (n <= 0) 1
    else n * factorial(n-1)
  }
  println(factorial(5))

  // can evaluate factorial for big number
  def bigFactorial(n: Int): BigInt = {
    @tailrec
    def factorialHelper(x: Int, accumulator: BigInt): BigInt =
      if (x <= 1) accumulator
      else factorialHelper(x - 1, x * accumulator) // TAIL RECURSION = use recursion in LAST position

    factorialHelper(n, 1)
  }
  println(bigFactorial(5000))

  // WHEN NEED LOOPS, USE TAIL RECURSION

  // concat string n times
  def stringConcat(a: String, n: Int): String = {
    @tailrec
    def concatHelper(m: Int, x: String, accumulator: String): String =
      if (m <= 0) accumulator
      else concatHelper(m-1, x, accumulator + x)

    concatHelper(n, a, "")
  }
  println(stringConcat("heh123", 20))

  // prime number with tail recursion
  def isPrime(n: Int): Boolean = {
    def isPrimeTailrec(t: Int, isStillPrime: Boolean): Boolean =
      if (!isStillPrime) false
      else if (t <= 1) true
      else isPrimeTailrec(t - 1, n % t != 0 && isStillPrime)

    isPrimeTailrec(n / 2, true)
  }
  println(isPrime(2003))
  println(isPrime(629))


}
