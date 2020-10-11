package lectures.beginner.part1basics

object StringOps extends App {

  val str: String = "Hello, I'm enjoy learning Scala!!!"

  println(str.charAt(2))
  println(str.substring(4, 11))
  println(str.length)
  println(str.split(" ").toList)

  val aNumberStr = "45"
  val aNumber = aNumberStr.toInt

  println('a' +: aNumberStr :+ 'z') // prepending, appending operators

  // Scala specific
  // S-interpolators
  println(s"TTTT $aNumberStr and ${aNumber / 5}")

  // F-interpolators
  val speed = 1.2f
  println(f"TTTT $aNumberStr and $speed%2.2f")

  // raw-interpolators
  println(raw"This char \n is new line")


}
