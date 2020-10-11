package lectures.advanced.part2afp

object PartialFunctions extends App {

  // Normal function
  val aFunction = (x: Int) => x + 1 // Function1[Int, Int] === Int => Int

  // Normal function that throw exception if not applicable to argument
  class FunctionNotApplicableException extends RuntimeException
  val aFussyFunction = (x: Int) =>
    if (x == 1) 42
    else if (x == 2) 56
    else if (x == 5) 999
    else throw new FunctionNotApplicableException

  // can be rewreted using PM => throw MatchError
  val aNicerFussyFunction = (x: Int) => x match {
    case 1 => 42
    case 2 => 56
    case 5 => 999
  }

  // Partial function works on subset of argument values {1,2,5} => Int
  val aPartialFunction: PartialFunction[Int, Int] = {
    case 1 => 42
    case 2 => 56
    case 5 => 999
  } // partial function value

  println(aPartialFunction(2))
  //  println(aPartialFunction(57273)) => throw MatchError

  // PF utilities
  println(aPartialFunction.isDefinedAt(67)) // check applicability

  // lift (PF -> normal function)
  val lifted = aPartialFunction.lift // Int => Option[Int]
  println(lifted(2))
  println(lifted(98))

  // chaining
  val pfChain = aPartialFunction.orElse[Int, Int] {
    case 45 => 67
  }

  println(pfChain(2))
  println(pfChain(45))

  // PF extend normal functions
  val aTotalFunction: Int => Int = {
    case 1 => 99
  }

  // HOFs accept partial functions as well
  val aMappedList = List(1,2,3).map {
    case 1 => 42
    case 2 => 78
    case 3 => 1000
  }
  println(aMappedList) // if in list we have arg that not matched to PF => MatchError

  /*
    Note: PF can only have ONE parameter type:
    trait PartialFunction[-A, +B] extends scala.AnyRef with scala.Function1[A, B]
   */

  /**
   * Exercises
   *
   * 1 - construct a PF instance yourself (anonymous class)
   * 2 - dumb chatbot as a PF
   */

  val pfInstance = new PartialFunction[Int, Int] {
    override def isDefinedAt(x: Int): Boolean =
      x == 1 || x == 2 || x == 3

    override def apply(v1: Int): Int = v1 match {
      case 1 => 10
      case 2 => 5
      case 3 => 0
    }
  }

  println(pfInstance(1))
  //println(pfInstance(2))
  //println(pfInstance(45))

  val dumbChatbot: PartialFunction[String, String] = {
    case s if s.contains("hello") => "Hi guy!"
    case s if s.contains("bye") => "Get good evening...:)"
  }
  scala.io.Source.stdin.getLines().map(dumbChatbot).foreach(println)
}
