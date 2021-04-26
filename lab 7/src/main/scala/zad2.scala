import scala.annotation.tailrec

object zad2 extends App {
  def swap[A](seq: Seq[A]): Seq[A] = {
    @tailrec
    def helper(seq: Seq[A], i:Int, seq1: Seq[A]): Seq[A] = {
      if (i>=seq.length){seq1}
      else {
        helper(seq, i + 2, seq1 ++ (seq.takeRight(seq.length-i).take(2).reverse))
      }
    }
    helper(seq,0,Seq())
  }
  println(swap(Seq(1, 2, 3, 4, 5,6,7)))
}