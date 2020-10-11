package lectures.beginner.part2oop

object Inheritance extends App {

  // single class inheritance
  // private, protected, no modifier (public)
  sealed class Animal {
    val creatureType: String = "wild"
    def eat(): Unit = println("mnomnom")
  }

  class Cat extends Animal {
    def crunch: Unit = {
      eat()
      println("crunch crunch")
    }
  }

  val cat  = new Cat
  cat.crunch

  // constructors
  class Person(name: String, age: Int) {
    def this(name: String) = this(name, 0)
  }
  class Adult(name: String, age: Int, idCard: String) extends Person(name, age)

  // overriding
  class Dog(override val creatureType: String) extends Animal {
    override def eat(): Unit = super.eat()
  }
  val doggy = new Dog("domestic")
  doggy.eat()

  // type substitution ("polymorphism")
  val x: Animal = new Dog("K9")
  println(x.creatureType)
  x.eat()

  // final prevent from overriding on: method, class, sealed class (only extend in this file)
}
