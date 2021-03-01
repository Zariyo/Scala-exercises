object zad extends App {
    (obramuj("super zadanko mam nadzieje ze jest dobrze"))
  def obramuj(napis: String): Unit = {
      val tablica= napis.split('\n').toList
      val koniec=tablica.map(s => "*" + s + "*")
      println("*"*(tablica.maxBy(s => s.length).length+2))
      koniec.foreach(println)
      println("*"*(tablica.maxBy(s => s.length).length+2))
  }
}