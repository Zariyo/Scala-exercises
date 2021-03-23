import scala.annotation.tailrec


object zad1 extends App {

  def sumuj(l: List[Option[Double]]): Option[Double] = {
    @tailrec
    def helper(lista: List[Option[Double]], acc: Double, indeks: Int): Option[Double] = {
      if (indeks==lista.length) {
        if (acc == 0) return None
        else return Option(acc)
      }
      lista(indeks) match {
        case Some(n) => {
          if (n>0) return helper(lista, acc+n, indeks+1)
          else return helper(lista, acc, indeks+1)
      }
        case None =>{
          return helper(lista, acc, indeks+1)
        }
    }
  }
    return helper(l, 0, 0)

}
println(sumuj(List(Some(2.0), Some(4.0), Some(-3.0), None, Some(-3.0), None, Some(1.0))))
println((List(Some(2.0), Some(4.0), Some(-3.0), None, Some(-3.0), None, Some(1.0))))
}












