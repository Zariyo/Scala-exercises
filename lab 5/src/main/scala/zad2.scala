object zad2 extends App {
  def lift[A, B, T](op: (T,T) => T)(f: A => T, g: B => T): (A, B) => T = {
    (a: A, b: B) => (op(f(a), g(b)))
  }
  type MSet[A] = A => Int

  val a:MSet[Int] = (n: Int) => n match {
    case 1 => 2
    case 3 => 1
    case _ => 0
  }

  val b:MSet[Int] = (n: Int) => n match {
    case 3 => 2
    case 5 => 3
    case 4 => 1
  }

  def sum[A](s1: MSet[A], s2: MSet[A]): MSet[A] = {
    a: A => s1(a) + s2(a)

  }

  def diff[A](s1: MSet[A], s2: MSet[A]): MSet[A] = {
    b: A => s1(b) - s2(b)
  }
  def mult[A](s1: MSet[A], s2: MSet[A]): MSet[A] = {
    c: A => s1(c)*s2(c)
  }
  println(sum(a, b)(3))
  println(diff(a, b)(3))
  println((mult(a, b)(3)))
}