object zad1 extends App {

  def subSeq[A](seq: Seq[A], begIdx: Int, endIdx: Int): Seq[A] = {
    println(seq)
    println(seq.drop(begIdx).take(endIdx-1))
    return seq
  }
  subSeq(Seq(1,2,3,4,5,6,7,8),2,4)
}