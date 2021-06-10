package lab12
import akka.actor._
import akka.actor.Actor
import akka.actor.Props

import scala.concurrent.duration._
import scala.util.Random


object Zad1 {

  val rnd = new scala.util.Random

  case class Przyjmij(n: Int, wrog: ActorRef)
  case class Zabij(n: Int)
  case object Zyjesz
  case class Strzal(zamek: ActorRef)
  case object Ognia
  case object Pocisk
  case object Wygrana



  class Nadzorca extends Actor {
    def receive: Receive = {
      case Przyjmij(n,wrog) => {
        val pracownicy = for (i <- 0 until n) yield context.actorOf(Props[Pracownik](), ((i).toString))
        context.become(Uformowany(pracownicy, 4*n,4*n, n, wrog))
      }




    }
    def Uformowany(pracownicy: IndexedSeq[ActorRef], Rezerwa: Int,RezerwaID: Int, baza: Int, wrog:ActorRef): Receive = {
      case Zabij(n) =>{
        for (i <- 0 until n) yield {
          pracownicy(i) ! PoisonPill

        }
        context.become(Uformowany(pracownicy.drop(n), Rezerwa,RezerwaID, baza, wrog))

      }
      case Strzal(zamek) =>{
        for (i <- 1 until pracownicy.length-1) yield {pracownicy(i) ! Strzal(zamek)}
      }
      case pocisk => {
        if (rnd.nextInt(199) + 1 < pracownicy.length) {
          self ! Zabij(1)
          if (pracownicy.length<0.5*baza){
            println(s"${self.path.name} uzupelniam, rezerwa: $Rezerwa")
            for (i <- 0 until pracownicy.length - 1) yield {
              pracownicy(i) ! PoisonPill}
            context.become(Uformowany(for (i <- baza*5-RezerwaID until (baza*5-RezerwaID+(baza))) yield context.actorOf(Props[Pracownik](), ((i).toString)), Rezerwa-(baza*0.5).toInt,RezerwaID-baza, baza, wrog))
          }

        }
        if (Rezerwa==0){
          println(s"${self.path.name} Przegralem!")
          wrog ! Wygrana
          context.become(poBitwie())
        }
      }
      case Wygrana =>{
        println(s"${self.path.name} Wygralem! Pozostalo: ${Rezerwa+pracownicy.length}")
        context.become(poBitwie())
      }
      }
    def poBitwie() : Receive ={
      case Wygrana =>{
        println("kox")
      }
      case msg => {

      }
    }

    }


  class Pracownik extends Actor {
    def receive: Receive = {
      case PoisonPill => context.stop(self)
      case Zyjesz => println(s"${self.path.name} zyje")
      case Terminated(actor) => println("Pracownik został zabity")
      case Strzal(zamek) => {
        zamek ! Pocisk
      }
    }
  }

  case class Tick(nadzorca1: ActorRef, nadzorca2: ActorRef)
  class TickActor extends Actor {
    def receive: Receive = {
      case Tick(nadzorca1, nadzorca2) =>{
        nadzorca1 ! Strzal(nadzorca2)
        nadzorca2 ! Strzal(nadzorca1)
      }
      case _ => {

      }
    }
  }



  def main(args: Array[String]): Unit = {



    val system = ActorSystem("sys")
    import system.dispatcher
    val nadzorca1 = system.actorOf(Props[Nadzorca](), "nadzorca1")
    val nadzorca2 = system.actorOf(Props[Nadzorca](), "nadzorca2")
    val p = system.actorOf(Props[Pracownik](), "p")
    val n = 20
    nadzorca1 ! Przyjmij(n,nadzorca2)
    nadzorca2 ! Przyjmij(n,nadzorca1)

    val tickActor = system.actorOf(Props[TickActor](), "defender")

    val ticker = system.scheduler.scheduleWithFixedDelay(
      Duration.Zero, 10.milliseconds, tickActor, Tick(nadzorca1, nadzorca2)
    )

    // system.terminate()
  }
}
