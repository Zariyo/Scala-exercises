object zad3 extends App {
  def insertInto[A](l: List[A], el: A)(leq: (A, A) => Boolean): List[A] = {
    def helper[A](l: List[A], el: A, leq: (A, A)=> Boolean, i: Int): List[A]= {
      if (i==l.length-1) return l
      if (leq(el, l(i))){
        return (el::l.take(i).reverse).reverse ++ l.drop(i)
      }
      else{ helper(l, el, leq, i+1) }
    }
    return helper(l,el,leq,0)
  }
  println(insertInto(List(1,2,3,4,5,6,4,6),3)(_ < _))
}