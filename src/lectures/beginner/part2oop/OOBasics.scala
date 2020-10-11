package lectures.beginner.part2oop

object OOBasics extends App {

  val person = new Person("Jelly", 33)
  person.greet()
  person.greet("Sallly")

}

// constructor
class Person(val name: String, val age: Int) {
  // fields
  val x = 2

  // method
  def greet(name: String): Unit = println(s"${this.name} says: Hello, $name")

  // overloading
  def greet(): Unit = println(s"I'm $name")

  // multi constructors
  def this(name: String) = this(name, 0)
  def this() = this("Unknown", 0)
}
