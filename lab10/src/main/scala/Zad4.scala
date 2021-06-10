package lab10
import akka.actor.{ActorSystem, Actor, ActorRef, Props}

object Zad4 {
    import akka.actor._
    case class Start(max: Int)
    case class Number(pracownicy: IndexedSeq[ActorRef],n : Int)
    case class checkNum(pracownicy: IndexedSeq[ActorRef], n: Int, i: Int)

    class Nadzorca extends Actor {
        def receive: Receive = {
            case Start(m) =>
                if (m >= 2) {
                    val pracownicy = for (n <- 2 until m + 3) yield (context.actorOf(Props[Pracownik] (),((n).toString)))
                    for (n <- 2 until m) yield { pracownicy(n) ! Number(pracownicy, n) }
                }
        }
    }

    class Pracownik extends Actor {
        def receive: Receive = {
            case Number(pracownicy, n) => {
                pracownicy(0) ! checkNum(pracownicy, n, 0)

            }
            case checkNum(pracownicy, m, i)=> {

                val n = (self.path.name).toInt
                if (n == m){
                    println(s"$m jest pierwsza")
                }
                else {
                    if (m % n == 0) {

                    }
                    else {
                        if (n==pracownicy.length-1){
                            println(s"$m jest pierwsza")
                        }
                        else {
                            pracownicy(i + 1) ! checkNum(pracownicy, m, i+1)
                        }
                    }
                }
            }


            case msg =>{
                println(msg)
            }
        }
    }

    def main(args: Array[String]): Unit = {
        val system = ActorSystem("Eratostenes")
        val nadzorca = system.actorOf(Props[Nadzorca] (),"nadzorca")
        nadzorca ! Start(2000000)
    }
}
