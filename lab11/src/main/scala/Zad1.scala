package lab11
import akka.actor.{ActorSystem, Actor, ActorRef, Props}

object Zad1 {

  def dane(): List[String] = {
    scala.io.Source.fromResource("ogniem_i_mieczem.txt")
      .getLines
      .toList
  }

  import akka.actor._

  case class Init(max: Int)

  case class Zadanie(tekst: List[String])

  case class checkNum(pracownicy: IndexedSeq[ActorRef], n: Int, i: Int)

  case object sayHi

  case class ZadaniePracownik(tekst: String)

  class Nadzorca extends Actor {
    def receive: Receive = {
      case Init(m) => {
        val pracownicy = for (i <- 1 until m) yield context.actorOf(Props[Pracownik](), ((i).toString))
        for (i <- 1 until m-1) yield {pracownicy(i) ! sayHi}
        context.become(przyjmowanie(m, pracownicy))

      }
    }
    def przyjmowanie(m: Int, pracownicy: IndexedSeq[ActorRef]): Receive = {
      case Zadanie(tekst) => {
        println(s"mam zadanie, ")
        for (i <- 1 until tekst.length) yield {pracownicy(i%(m-1)) ! ZadaniePracownik(tekst(i-1).toLowerCase)}
      }
    }
  }

  class Pracownik extends Actor {
    def receive: Receive = {
      case `sayHi` => {

      }
      case ZadaniePracownik(tekst) => {
        println(tekst.split(" ").groupBy((word:String) => word).mapValues(_.length).toMap)

      }
      case checkNum(pracownicy, m, i) => {

      }
    }

    }



  def main(args: Array[String]): Unit = {
    val system = ActorSystem("TextSystem")
    val nadzorca = system.actorOf(Props[Nadzorca](), "nadzorca")
    nadzorca ! Init(20)

    val tekst = dane()
    nadzorca ! Zadanie(tekst)
  }
}
