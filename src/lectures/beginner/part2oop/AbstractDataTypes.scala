package lectures.beginner.part2oop

object AbstractDataTypes extends App {

  // abstract
  abstract class Animal {
    val creatureType: String = "animal"
    def eat: Unit
  }

  class Dog extends Animal {
    override val creatureType: String = "Canine"
    def eat: Unit = println("crunch crunch")
  }

  // traits (like interfaces)
  trait Carnivore {
    def eat(animal: Animal): Unit
  }
  trait ColdBlooded

  class Croc extends Animal with Carnivore with ColdBlooded {
    override val creatureType: String = "croco"
    override def eat: Unit = println("nmomnom")
    override def eat(animal: Animal): Unit = println(s"I like eating ${animal.creatureType}")
  }

  val doggy = new Dog
  val croco = new Croc
  croco.eat(doggy)

  // traits vs abstract classes
  // 1. traits has no constructor
  // 2. traits = behavior
  // 3. class can implement many traits


}
