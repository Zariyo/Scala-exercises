import scala.annotation.tailrec


object zad3 extends App {

  def usun[A](l: List[A], el: A): List[A] = {
    @tailrec
    def helper[A](lista: List[A], el: A, indeks: Int): List[A] = {
      if (indeks==lista.length) return lista
      lista(indeks) match {
        case `el` => {
          val store = lista.take(indeks) ++ lista.drop(indeks+1)
          helper(store, el, indeks+1)
        }
        case _ => {
          helper(lista, el, indeks+1)
        }
      }
    }
    return helper(l, el, 0)
  }
  println(usun(List(2, 1, 4, 1, 3, 3, 1, 2), 1))
}


