package lab10
import akka.actor.{ActorSystem, Actor, ActorRef, Props}

object Zad3 {

  case object Pileczka
  case class Ping(obiekt: Object,actors: IndexedSeq[ActorRef], next: ActorRef,i : Int, n: Int)
  case class Pong(obiekt: Object,actors: IndexedSeq[ActorRef], next: ActorRef,i: Int, n: Int)


  class Gracz extends Actor {
    def receive: Receive = {
      case Ping(Pileczka,actors, next,i, n) => {
        println(s"${self.path.name} ping!")
        next ! Pong(Pileczka,actors, actors(i+1),(i+1)%(n-1), n)
      }
      case Pong(Pileczka,actors, next,i, n) => {
        println(s"${self.path.name} pong!")
        next ! Ping(Pileczka,actors, actors(i+1),(i+1)%(n-1), n)
      }
    }
  }

  def main(args: Array[String]): Unit = {
    val system = ActorSystem("sys")
    val n=25
    val actors = for (i <- 0 until n) yield (system.actorOf(Props[Gracz] (),("gracz"+i.toString)))
    actors(0) ! Ping(Pileczka, actors, actors(1),0, n)
  }

}
