object zad2 extends App {
  def jestPalindromem(tab: Array[Int]): Boolean = {
    val rozmiar = tab.length - 1
    if (rozmiar == 0 || rozmiar == -1) return true
    else if (tab(0)==tab(rozmiar))
      jestPalindromem(tab.drop(1).dropRight(1))

    else false
  }
  println(jestPalindromem(Array(1,2,3,3,5,3,2,1)))
}

