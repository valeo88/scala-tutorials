package lectures.beginner.part2oop

object Objects extends App {

  // Scala has not Class-level functionality ("static")

  object Person {
    // "static" functionality
    val N_EYES = 2
    def canFly: Boolean = false
    // factory method to create persons
    def apply(mother: Person, father: Person): Person = new Person("child")
  }
  class Person(val name: String) {
    // instance functionality
  }
  // object + class = COMPANIONS

  println(Person.N_EYES)
  println(Person.canFly)

  // Scala objects = singleton instance
  val mary = new Person("John")
  val john = new Person("John")
  println(mary == john)

  // widely used
  val child = Person(mary, john)

  // Scala applications
  // def main(args: Array[String]): Unit

}
