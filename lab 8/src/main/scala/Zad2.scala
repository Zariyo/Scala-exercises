object Zad2 {

  def main(args: Array[String]): Unit = {
    val linie = io.Source
      .fromResource("nazwiska.txt")
      .getLines.toList
    val odp = linie.filter(x => x.distinct.length==x.length).min
    println(odp)
  }

}
