import scala.annotation.tailrec


object zad3 extends App {

  def ciag(n: Int): Int = {
    @tailrec
    def helper(n: Int, sum: Int, add: Int, step: Int): Int = {
      if (step==n){
        return sum
      }
      else{
        helper(n, sum+add, sum, step+1)
      }
    }
    return helper(n, 0, 1, 0)
  }
  println(ciag(9))
}


