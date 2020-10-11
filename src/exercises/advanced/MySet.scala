package exercises.advanced

import scala.annotation.tailrec

/** Functional set */
trait MySet[A] extends (A => Boolean) {
  // can use MySet(element) to check element existence
  def apply(element: A): Boolean = contains(element)

  def contains(element: A): Boolean
  // add element to set
  def +(element: A): MySet[A]
  // union of this and anoter set
  def ++(another: MySet[A]): MySet[A]
  // remove element from set
  def -(element: A): MySet[A]
  // difference this and another
  def --(another: MySet[A]): MySet[A]
  // intersection this and another
  def &(another: MySet[A]): MySet[A]
  // negation: !MySet(1,2,3) = MySet of every number but not 1,2,3
  def unary_! : MySet[A]

  def map[B](f: A => B): MySet[B]
  def flatMap[B](f: A => MySet[B]): MySet[B]
  def filter(predicate: A => Boolean): MySet[A]
  def foreach(f: A => Unit): Unit
}

/** Set that contains no elements */
class EmptySet[A] extends MySet[A] {
  override def contains(element: A): Boolean = false

  override def +(element: A): MySet[A] = new LinkedSet(element, this)
  override def ++(another: MySet[A]): MySet[A] = another
  override def -(element: A): MySet[A] = this
  override def --(another: MySet[A]): MySet[A] = this
  override def &(another: MySet[A]): MySet[A] = this
  override def unary_! : MySet[A] = new PropertyBasedSet[A](_ => true)

  override def map[B](f: A => B): MySet[B] = new EmptySet
  override def flatMap[B](f: A => MySet[B]): MySet[B] = new EmptySet
  override def filter(predicate: A => Boolean): MySet[A] = this
  override def foreach(f: A => Unit): Unit = ()
}

/** Set of all elements of type A, for that property(A) = true.
  math notation: { x in A | property(x) }
  this is potential infinite set. */
class PropertyBasedSet[A](property: A => Boolean) extends MySet[A] {
  override def contains(element: A): Boolean = property(element)

  override def +(element: A): MySet[A] =
    new PropertyBasedSet[A](x => property(x) || x == element)
  override def ++(another: MySet[A]): MySet[A] =
    new PropertyBasedSet[A](x => property(x) || another(x))
  override def -(element: A): MySet[A] = filter(_ != element)
  override def --(another: MySet[A]): MySet[A] = filter(!another)
  override def &(another: MySet[A]): MySet[A] = filter(another)
  override def unary_! : MySet[A] = new PropertyBasedSet[A](x => !property(x))

  // can't define
  override def map[B](f: A => B): MySet[B] = fail
  override def flatMap[B](f: A => MySet[B]): MySet[B] = fail
  override def foreach(f: A => Unit): Unit = fail
  // used based property and predicate
  override def filter(predicate: A => Boolean): MySet[A] = new PropertyBasedSet[A](x => property(x) && predicate(x))

  def fail = throw new IllegalArgumentException("Can't apply this operation")
}

/** Set with links: head -> tail, contains minimum 1 element. */
class LinkedSet[A](val head: A, val tail: MySet[A]) extends MySet[A] {
  override def contains(element: A): Boolean = element == head || tail.contains(element)

  override def +(element: A): MySet[A] =
    if (this contains element) this
    else new LinkedSet[A](element, this)

  override def ++(another: MySet[A]): MySet[A] = tail ++ another + head

  override def -(element: A): MySet[A] =
    if (head == element) tail
    else tail - element + head

  override def &(another: MySet[A]): MySet[A] = filter(another) // intersection == filtering!

  override def --(another: MySet[A]): MySet[A] = filter(!another) // difference == filtering

  override def unary_! : MySet[A] = new PropertyBasedSet[A](x => !contains(x))

  override def map[B](f: A => B): MySet[B] = (tail map f) + f(head)

  override def flatMap[B](f: A => MySet[B]): MySet[B] = (tail flatMap f) ++ f(head)

  override def foreach(f: A => Unit): Unit = {
    f(head)
    tail foreach f
  }

  override def filter(predicate: A => Boolean): MySet[A] =
    if (predicate(head)) (tail filter predicate) + head
    else tail filter predicate
}

/** Companion object with method to create sets from varargs. */
object MySet {
  // A* - varargs
  def apply[A](values: A*): MySet[A] = {
    @tailrec
    def buildSet(seq: Seq[A], accumulator: MySet[A]): MySet[A] = {
      if (seq.isEmpty) accumulator
      else buildSet(seq.tail, accumulator + seq.head)
    }
    buildSet(values.toSeq, new EmptySet[A])
  }
}

object TestSet extends App {
  println("Set operations")
  val set = MySet(1,2,3)
  set + 5 ++ MySet(-1,-2) + 3 map (_ * 2) filter (_ % 2 == 0) foreach println

  println()
  println("Test difference")
  set -- MySet(2) foreach println

  println()
  println("Test intersection")
  set & MySet(1,2) foreach println

  println()
  println("Negative set")
  val negative = !set
  println(negative(2))
  println(negative(5))


}
