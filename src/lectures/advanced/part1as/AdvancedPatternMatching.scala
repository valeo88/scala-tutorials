package lectures.advanced.part1as

object AdvancedPatternMatching extends App {

  val numbers = List(1)
  val description = numbers match {
    case head :: Nil => println(s"the only element is $head.") // infix pattern
    case _ =>
  }

  /*
    - constants
    - wildcards
    - case classes
    - tuples
    - some special magic like above
   */

  // if we have NON-CASE class, we need to define unapply method(s) in companion object to use pattern matching
  class Person(val name: String, val age: Int)

  object Person {
    /* for matching like: [Person] match {
      case Person(String, Int) => ...
    } */
    def unapply(person: Person): Option[(String, Int)] =
      if (person.age < 21) Option.empty
      else Option((person.name, person.age))

    /* for matching like: [Int] match {
      case Person(String) => ...
    } */
    def unapply(age: Int): Option[String] =
      Option(if (age < 21) "minor" else "major")
  }

  val bob = new Person("Bob", 25)
  val greeting = bob match {
    case Person(n, a) => s"Hi, my name is $n and I am $a yo."
  }

  println(greeting)

  val legalStatus = bob.age match {
    case Person(status) => s"My legal status is $status"
  }

  println(legalStatus)

  // Exercise
  // How to rewrite in better way this pattern matching expression?
  val n: Int = 45
  val mathProperty = n match {
    case x if x < 10 => "goooo"
    case x if x % 2 == 0 => "booo"
    case _ => "haaa"
  }

  // use objects (name in lowercase) with unapply methods for each condition
  object even {
    def unapply(arg: Int): Boolean = arg % 2 == 0
  }

  object singleDigit {
    def unapply(arg: Int): Boolean = arg > -10 && arg < 10
  }

  val mathPropertyEnhanced = n match {
    case singleDigit() => "goooo"
    case even() => "booo"
    case _ => "haaa"
  }

  println(mathPropertyEnhanced)

  // Infix patterns (like ::)
  case class Or[A, B](a: A, b: B)
  val either = Or(2, "two")
  val humanDescription = either match {
    case number Or string => s"$number is written as $string" // = Or(number, string)
  }
  println(humanDescription)

  // Decomposing sequences
  val vararg = numbers match {
    case List(1, _*) => "starting with 1" // 1 in head and other elements no matter
  }

  // in case of our data structure we need to define unapplySeq
  abstract class MyList[+A] {
    def head: A = ???
    def tail: MyList[A] = ???
  }
  case object Empty extends MyList[Nothing]
  case class Cons[+A](override val head: A, override val tail: MyList[A]) extends MyList[A]

  object MyList {
    // MyList[A] => Option[Seq[A]] (sequence of elements)
    def unapplySeq[A](list: MyList[A]): Option[Seq[A]] =
      if (list == Empty) Some(Seq.empty)
      else unapplySeq(list.tail).map(list.head +: _)
  }

  val myList: MyList[Int] = Cons(1, Cons(2, Cons(3, Empty)))
  val decomposed = myList match {
    case MyList(1, 2, _*) => "starting with 1, 2" // because we implement unapplySeq method
    case _ => "something else"
  }

  println(decomposed)

  // Custom return types for unapply
  // NEED to impelement methods: isEmpty, get like in Option
  abstract class Wrapper[T] {
    def isEmpty: Boolean
    def get: T
  }

  object PersonWrapper {
    // can return custom type Wrapper
    def unapply(person: Person): Wrapper[String] = new Wrapper[String] {
      def isEmpty = false
      def get: String = person.name
    }
  }

  println(bob match {
    case PersonWrapper(name) => s"This person's name is $name"
    case _ => "An alien"
  })


}
