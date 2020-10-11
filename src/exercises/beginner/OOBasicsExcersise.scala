package exercises.beginner

object OOBasicsExcersise extends App {
  val author = new Writer("John", "Snow", 1976)
  val imposter = new Writer("John", "Snow", 1976)
  println(author.fullname)

  val favorite = new Novel("Who is he?", 1997, author)
  println(favorite.isWrittenBy(author))
  println(favorite.isWrittenBy(imposter))

  val counter = new Counter
  counter.inc.print
  counter.inc.inc.print
  counter.inc(10).print
  counter.dec.print
}

class Writer(firstname: String, surname: String, val year: Int) {
  def fullname = s"$firstname $surname"
}

class Novel(name: String, yearOfRelease: Int, author: Writer) {
  def authorAge: Int = yearOfRelease - author.year

  def isWrittenBy(author: Writer): Boolean = this.author == author

  def copy(newYearOfRelease: Int): Novel = new Novel(name, newYearOfRelease, author)
}

class Counter(count: Int = 0) {
  // new instance for immutability
  def inc: Counter = {
    println("incrementing")
    new Counter(count + 1)
  }
  def dec: Counter = {
    println("decrementing")
    new Counter(count - 1)
  }

  // tail recursion increment/decrement - Scala way
  def inc(amount: Int): Counter = {
    if (amount <= 0) this
    else inc.inc(amount - 1)
  }

  def dec(amount: Int): Counter = {
    if (amount <= 0) this
    else dec.dec(amount - 1)
  }

  def print: Unit = println(count)
}