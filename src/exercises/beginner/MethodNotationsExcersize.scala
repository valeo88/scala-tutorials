package exercises.beginner

object MethodNotationsExcersize extends App {

  class Person(val name: String, val age: Int, favoriteMovie: String) {
    def likes(movie: String): Boolean = movie == favoriteMovie
    def +(title: String): Person = new Person(s"$name ($title)", age, favoriteMovie)
    def unary_+ : Person = new Person(name, age + 1, favoriteMovie)
    def learns(topic: String): String = s"$name learns $topic"
    def learnsScala: String = this learns "Scala"
    def apply(times: Int): String = s"$name watched $favoriteMovie $times times"
  }

  val mary = new Person("Mary", 18,"Inception")
  val maryRockstar = mary + "the rockstar"
  println(maryRockstar.name)

  println((+mary).age)

  println(mary.learnsScala)

  println(mary(2))

}
