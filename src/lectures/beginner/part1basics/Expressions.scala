package lectures.beginner.part1basics

object Expressions extends App {

  // expressions
  val x = 1 + 2
  println(x)

  // math expression
  // + - * / | & ^ >> << >>>
  println(2 + 3 * 4)

  // boolean expressions
  // == != <= >= < > ! && ||
  println(1 == x)
  println(!(1 == x))

  // += -= *= /=
  var aVar = 4
  aVar += 5

  // INSTRUCTIONS (executed) and EXPRESSIONS (evaluated)

  // IF expresion
  val aCondition = true
  val aConditionedValue = if(aCondition) 5 else 3
  println(aConditionedValue)

  // never use while (...) {...} loops
  // Everything in Scala is an Expression!

  val aWeirdValue: Unit = (aVar = 3) // Unit === void

  // Code blocks
  val aCodeBlock: String = {
    // not visible outside
    val x = 4
    println(x + 5)

    if (x > 5) "hello" else "goodbye"
  }

}
