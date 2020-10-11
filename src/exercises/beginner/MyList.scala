package exercises.beginner

/*
    1.  Generic trait MyPredicate[-T] with a little method test(T) => Boolean
    2.  Generic trait MyTransformer[-A, B] with a method transform(A) => B
    3.  MyList:
        - map(transformer) => MyList
        - filter(predicate) => MyList
        - flatMap(transformer from A to MyList[B]) => MyList[B]
        class EvenPredicate extends MyPredicate[Int]
        class StringToIntTransformer extends MyTransformer[String, Int]
        [1,2,3].map(n * 2) = [2,4,6]
        [1,2,3,4].filter(n % 2) = [2,4]
        [1,2,3].flatMap(n => [n, n+1]) => [1,2,2,3,3,4]
   */

abstract class MyList[+A] {
  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyList[B]
  def printElements: String
  // polymorphic call
  override def toString: String = "[" + printElements + "]"
  def map[B](transformer: A => B): MyList[B]
  def filter(predicate: A => Boolean): MyList[A]
  def flatMap[B](transformer: A => MyList[B]): MyList[B]
  def ++[B >: A](list: MyList[B]): MyList[B]

  def foreach(action: A => Unit): Unit
  def sort(compare: (A, A) => Int): MyList[A]
  def zipWith[B, C](list: MyList[B], zipper: (A, B) => C): MyList[C]
  def fold[B](start: B)(function: (B, A) => B): B
}

case object Empty extends MyList[Nothing] {
  override def head: Nothing = throw new NoSuchElementException
  override def tail: MyList[Nothing] = throw new NoSuchElementException
  override def isEmpty: Boolean = true
  override def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)
  override def printElements: String = ""
  override def map[B](transformer: Nothing => B): MyList[B] = Empty
  override def filter(predicate: Nothing => Boolean): MyList[Nothing] = Empty
  override def flatMap[B](transformer: Nothing => MyList[B]): MyList[B] = Empty
  override def ++[B >: Nothing](list: MyList[B]): MyList[B] = list

  override def foreach(action: Nothing => Unit): Unit = ()
  override def sort(compare: (Nothing, Nothing) => Int): MyList[Nothing] = Empty
  override def zipWith[B, C](list: MyList[B], zipper: (Nothing, B) => C): MyList[C] =
    if (!list.isEmpty) throw new RuntimeException("Lists do not have the same length")
    else Empty
  override def fold[B](start: B)(function: (B, Nothing) => B): B = start
}

case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  override def head: A = h
  override def tail: MyList[A] = t
  override def isEmpty: Boolean = false
  override def add[B >: A](element: B): MyList[B] = new Cons(element, this)

  override def printElements: String =
    if (t.isEmpty) "" + h
    else h + " " + t.printElements

  override def map[B](transformer: A => B): MyList[B] =
    Cons(transformer(h), tail.map(transformer))

  override def filter(predicate: A => Boolean): MyList[A] =
    if (predicate(h)) Cons(h, tail.filter(predicate))
    else tail.filter(predicate)

  override def flatMap[B](transformer: A => MyList[B]): MyList[B] =
    transformer(h) ++ t.flatMap(transformer)

  override def ++[B >: A](list: MyList[B]): MyList[B] = Cons(h, t ++ list)

  override def foreach(action: A => Unit): Unit = {
    action(h)
    t.foreach(action)
  }

  override def sort(compare: (A, A) => Int): MyList[A] = {
    // insert sort
    def insert(x: A, sortedList: MyList[A]): MyList[A] =
      if (sortedList.isEmpty) Cons(x, Empty)
      else if (compare(x, sortedList.head) <= 0) Cons(x, sortedList)
      else Cons(sortedList.head, insert(x, sortedList.tail))

    val sortedTail = t.sort(compare)
    insert(h, sortedTail)
  }

  override def zipWith[B, C](list: MyList[B], zipper: (A, B) => C): MyList[C] =
    if (list.isEmpty) throw new RuntimeException("Lists have not same length")
    else Cons(zipper(h, list.head), t.zipWith(list.tail, zipper))

  override def fold[B](start: B)(function: (B, A) => B): B =
    t.fold(function(start, h))(function)
}

object ListTest extends App {
  val listOfInt: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val listOfIntClone: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val anotherListOfIntegers: MyList[Int] = new Cons(4, new Cons(5, Empty))
  val listOfString: MyList[String] = new Cons("Strin", new Cons("222", Empty))
  println(listOfInt.toString)
  println(listOfString.toString)

  println(listOfInt.map(_ * 2).toString)

  println((listOfInt ++ anotherListOfIntegers).toString)

  println(listOfInt.flatMap((value: Int) => Cons(value, Cons(value + 1, Empty))))

  println(listOfInt == listOfIntClone)

  listOfInt.foreach(x => println(x))

  println(listOfInt.sort((x,y) => y - x))

  println(listOfInt.zipWith(listOfIntClone, (x: Int, y: Int) => x * y))

  println(listOfInt.fold(0)((x, y) => x + y))

  // for-comprehensions
  for {
    n <- listOfInt
  } println(n)

  val forMap = for {
    n <- listOfInt
  } yield n * 2
  println(forMap)

  val forFilter = for {
    n <- listOfInt //if n % 2 == 0 not work
  } yield n
  println(forFilter)

  // comninations
  val combination = for {
    n <- listOfInt
    k <- listOfInt
  } yield n+k
  println(combination)
}