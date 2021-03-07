object zad1 extends App {
  def czyPierwsza(i :Int) : Boolean = {
    if (i <= 1)
      false
    else if (i == 2)
      true
    else
      !(2 until i).exists(x => i % x == 0)
  }
  println("Podaj liczbe do sprawdzenia")
  println(czyPierwsza(io.StdIn.readInt()))
}

