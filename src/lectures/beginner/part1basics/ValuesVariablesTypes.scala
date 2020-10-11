package lectures.beginner.part1basics

object ValuesVariablesTypes extends App {

  // vals are immutable
  // compiler can infer types
  val x = 43
  println(x)

  val aString: String = "hello"
  val bString = "goodbye"

  val aBoolean: Boolean = true
  val aChar: Char = 'a'
  val anInt: Int = 44
  val aShort: Short = 5353
  val aLong: Long = 744664664664646L
  val aFloat: Float = 2.0f
  val aDouble: Double = 3.112124

  // variables are mutable
  var aVar: Int = 4
  aVar = 5 // can be side effects



}
