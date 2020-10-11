package lectures.beginner.part1basics

object DefaultAndNamedArgs extends App {

  // default arg
  def trFact(n: Int, acc: Int = 1): Int = {
    if (n <= 1) acc
    else trFact(n-1, n * acc)
  }
  println(trFact(10))

  // named args
  def greet(name: String, age: Int): String =
    s"Hello $name, your age is $age"
  println(greet(name = "Saly", 10))
}
