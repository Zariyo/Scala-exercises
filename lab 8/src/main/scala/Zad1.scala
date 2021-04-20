object Zad1 {

  def main(args: Array[String]): Unit = {
    val linie = io.Source
      .fromResource("liczby.txt")
      .getLines.toList
    println(linie)
    def sumaniepar(arg: List[String], step: Int): String = {
      if (step>arg) suma
      val suma: String = arg.reduceLeft(_ % step + _ % step)
      sumaniepar(arg, step*10)
    }
  }

}
