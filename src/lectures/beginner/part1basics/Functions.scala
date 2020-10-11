package lectures.beginner.part1basics

object Functions extends App {

  // with params
  def aFunction(a: String, b: Int): String = {
    a + " " + b
  }
  println(aFunction("hello", 3))

  // no params
  def aParamLess(): Int = 42
  println(aParamLess())
  println(aParamLess)

  // WHEN YOU NEED LOOPS -> USE RECURSION
  // recursive functions must have return type
  def aRepeatFunction(a: String, n: Int): String = {
    if (n == 1) a
    else a + aRepeatFunction(a, n - 1)
  }
  println(aRepeatFunction("hello", 3))

  // a function, returns Unit, has side effect!

  // one function can be defined inside other
  def aBigFunction(n: Int): Int = {
    def aSmallFunction(a: Int, b: Int): Int = a + b

    aSmallFunction(n, n-1)
  }
  println(aBigFunction(4))

  // greeting function
  def greet(name: String, age: Int): String = {
    "Hi, my name is " + name + " and I'm " + age + " years old"
  }
  println(greet("Catty", 32))

  // factorial
  def nFactorial(n: Int): Int = {
    if (n <= 0) 1
    else n * nFactorial(n-1)
  }
  println(nFactorial(5))

  // fibonacci
  def fibonacci(n: Int): Int = {
    if (n == 1 || n == 2) 1
    else fibonacci(n-1) + fibonacci(n-2)
  }
  println(fibonacci(10))

  // is prime number
  def isPrimeNumber(n: Int): Boolean = Range(2, n-1).filter(n % _ == 0).length == 0
  println(isPrimeNumber(32))
}
