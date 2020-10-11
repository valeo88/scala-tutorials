package lectures.beginner.part2oop

object MethodNotations extends App {

  class Person(val name: String, favoriteMovie: String) {
    def likes(movie: String): Boolean = movie == favoriteMovie
    def +(person: Person): String = s"${this.name} is hanging out ${person.name}"
    def unary_! : String = s"${name} what a Hack"
    def isAlive: Boolean = true
    def apply(): String = "Applied" // signature matters
  }

  val mary = new Person("Mary", "Inception")
  println(mary.likes("Inception"))
  println(mary likes "Inception") // natural language way
  // infix notation = operator notation (syntactic sugar)

  // "operators" in Scala
  val jake = new Person("Jake", "Perception")
  println(mary + jake)

  println(1 + 2)
  println(1.+(2))

  // ALL OPERATORS ARE METHODS

  // prefix notation
  val x = -1 // eq. 1.unary_- | for -, +, !, ~

  println(!mary)
  println(mary.unary_!)

  // postfix notation
  println(mary.isAlive)
  //println(mary isAlive)

  //apply
  println(mary.apply())
  println(mary()) // eq

}
