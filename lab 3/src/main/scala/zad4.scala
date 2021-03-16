
import scala.annotation.tailrec


object zad4 extends App {

  def maksimum(l1: List[Double], l2: List[Double]): List[Double] = {
    @tailrec
    def helper(l1: List[Double], l2: List[Double],l3: List[Double], index: Int): List[Double] = {
      if (l1.length>=l2.length){
        if (index==l1.length){
          return l3
        }
      }
      if (l1.length<=l2.length){
        if (index==l2.length){
          return l3
        }
      }
      if (l1(index) > l2(index)){
        return helper(l1, l2, l3.updated(index, l1(index)), index + 1)
        }
      else {
        return helper(l1, l2, l3.updated(index, l2(index)), index + 1)
      }
    }
    if (l1.length>l2.length) {
      return helper(l1, l2, l1, 0)
    }
    else{
      return helper(l1, l2, l2, 0)
    }
  }
  println(maksimum(List(1,2,3,4), List(1,3,5,2)))
}


