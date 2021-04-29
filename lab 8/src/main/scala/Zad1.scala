object Zad1 {

  def main(args: Array[String]): Unit = {
    val linie = io.Source
      .fromResource("liczby.txt")
      .getLines.toList
    val nieMalejace = linie.filter(x => x.toList.sortWith( _ < _ ).mkString.equals(x))
    print(nieMalejace)
    println("###")
    val odp = nieMalejace.filter(x => x.toList.foldLeft(0)((m,n)=>(m+n.toInt))%2==1)
    println(odp)
    }


}
