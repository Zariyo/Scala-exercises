object zad4 extends App {

  def freq[A](seq: Seq[A]): Set[(A, Int)] = {
    seq.groupBy(identity).mapValues(_.size).toSet
  }
  print(freq(Seq('a','b','a','c','c','a')))
}