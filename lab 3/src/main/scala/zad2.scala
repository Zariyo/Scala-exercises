import scala.annotation.tailrec


object zad2 extends App {

  def jestpierwsza(n: Int): Boolean = {
    @tailrec
    def helper(n: Int, stepdown: Int): Boolean = {
      if(stepdown==1){
        return true
      }
      else if (n%stepdown==0) {
        return false
      }
      else{
        helper(n, stepdown-1)
      }
    }
    return helper(n, n-1)
  }
  println(jestpierwsza(8))
}


