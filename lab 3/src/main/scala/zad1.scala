import scala.annotation.tailrec


object zad1 extends App {

  def reverse(s: String): String = {
    @tailrec
    def helper(s: String, acc: String): String = {
      if (s == "") {
        return acc
      }
      else {
        helper(s.tail, s.head + acc)
      }
    }

    return helper(s, "")
  }
  println(reverse("kotkamala"))
}


