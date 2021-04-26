import scala.collection.immutable.Range.Int

object zad1 extends App {

  def indices[A](seq: Seq[A], el: A): Set[Int] = {
    (seq.zipWithIndex.filter { case (v, _) => v==el}.map{ case (_,v) => v}.toSet)

    }

  println(indices(Seq(1,2,1,1,5), 1))
}