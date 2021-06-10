package lab10
import akka.actor.{ActorSystem, Actor, ActorRef, Props}

object Zad2 {

  case object Pileczka
  case class Ping(obiekt: Object, other: ActorRef, other2: ActorRef)
  case class Pong(obiekt: Object, other: ActorRef, other2: ActorRef)


  class Gracz extends Actor {
    def receive: Receive = {
      case Ping(Pileczka, other, other2) => {
        println(s"${self.path.name} ping!")
        other ! Pong(Pileczka, self, other2)
      }
      case Pong(Pileczka, other, other2) => {
        println(s"${self.path.name} pong!")
        other2 ! Ping(Pileczka, other, self)
      }
    }
    }

  def main(args: Array[String]): Unit = {
    val system = ActorSystem("sys")
    val gracz1 = system.actorOf(Props[Gracz] (),"gracz1")
    val gracz2 = system.actorOf(Props[Gracz] (),"gracz2")
    val gracz3 = system.actorOf(Props[Gracz] (),"gracz3")
    gracz1 ! Ping(Pileczka, gracz2, gracz3)
  }

}
