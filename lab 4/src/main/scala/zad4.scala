
import scala.annotation.tailrec


object zad4 extends App {

  def divide[A](l: List[A]): (List[A], List[A]) = {
    @tailrec
    def helper[A](lista: List[A],lista1: List[A], lista2: List[A], indeks: Int): (List[A], List[A]) = {
      if (indeks == lista.length) return (lista1, lista2)
      val modulo = indeks%2
      modulo match {
        case 0 => {
          return helper(lista, (lista1 ++ List(lista(indeks))).updated(lista1.length, lista(indeks)), lista2, indeks+1)
        }
        case 1 => {
          return helper(lista, lista1, (lista2 ++ List(lista(indeks))).updated(lista2.length, lista(indeks)), indeks+1)
        }
      }
    }
    return helper(l, List(), List(), 0)
  }
  println(divide(List(1, 3, 5, 6, 7)))
}


