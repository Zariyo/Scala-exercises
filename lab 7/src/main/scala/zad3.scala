object zad3 extends App {
  def minNotContained(set: Set[Int]): Int = {
    val set2: Set[Int] = (set.filter(_ > 0).min to set.filter(_ > 0).max).toSet
    (set2.diff(set).min)
  }
  println(minNotContained(Set(-3, 0, 1, 2, 5, 6)))
}