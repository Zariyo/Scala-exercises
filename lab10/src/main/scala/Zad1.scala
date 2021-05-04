package lab10
import akka.actor.{ActorSystem, Actor, ActorRef, Props}

object Zad1 {

  case class Wynik(liczba1: Double, liczba2: Double)
  case object Zmien

  class Pracownik extends Actor {
    def receive: Receive = {
      case Wynik (a, b)=>println(a+b)
      case Zmien => context.become(wykonaj)
    }
    def wykonaj: Receive = {
      case Wynik(a, b)=> println(a*b)
      case Zmien => context.become(receive)
    }
  }

  def main(args: Array[String]): Unit = {
    val system = ActorSystem("sys")
    val pracownik = system.actorOf(Props[Pracownik] (),"pracownik")
    pracownik ! Wynik(2,3)
    pracownik ! Zmien
    pracownik ! Wynik(2,3)
    pracownik ! Wynik(1,4)
    pracownik ! Zmien
    pracownik ! Wynik(3,6)
  }

}
