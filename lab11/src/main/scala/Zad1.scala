package lab11

import akka.actor._


class MyActor extends Actor {
  def receive: Receive = {
    case msg => println(s"Odebrałem wiadomość: $msg")
  }
}



object Main {

  val system = akka.actor.ActorSystem("system")
  def dane(): List[String] = {
    scala.io.Source.fromResource("ogniem_i_mieczem.txt").getLines().toList

  }

  case class Init(liczbaPracownikow: Int)

  case class Zlecenie(tekst: List[String])

  case class Wykonaj( /* argumenty */ )

  case class Wynik( /* argumenty */ )


  class Nadzorca extends Actor {

    def receive: Receive = {
      case Init(liczba) =>{
        val pracownik = system.actorOf(Props[Pracownik], liczba.toString)
        pracownik ! Zlecenie(List("123","345"))
      }
    }

  }

  class Pracownik extends Actor {
    def receive: Receive = {
      case Zlecenie(tekst) => {
        print(tekst)
      }
    }
  }


  def main(args: Array[String]): Unit = {
    val system = ActorSystem("HaloAkka")
    val panNadzorca = system.actorOf(Props[Nadzorca], "panNadzorca")
    panNadzorca ! Init(50)
    println(dane())
  }

}
