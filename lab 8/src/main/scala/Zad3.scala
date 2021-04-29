object Zad3 extends App{

    val linie = io.Source
      .fromResource("ogniem-i-mieczem.txt")
      .getLines.toList

    def histogram(maks: Int): Unit = {
      val linieLower = linie.map(x => x.toLowerCase).toString().filter(_.isLetter).groupBy(x => x).toList.sortBy(x=> x._1).map(x => (x._1,x._2.length)).
        toList.map(x=>x._1+": " +
        (if (x._2>maks){
        "*"*maks+"\n"
      }else{
        "*"*x._2+"\n"
      }
        ))
      println(linieLower)

    }
  histogram(40)

}
