object zad3 extends App {

  def remElems[A](seq: Seq[A], k: Int): Seq[A] = {
    val seq1 = seq.zipWithIndex
    return seq1.filter {case (_, v) => v != k}.map{case (a, _) => a}
  }
  print(remElems(Seq(1,2,3,4,5,6,7,8),2))
}