
// TROT

import scala.util.Try
import scala.util.control.NonFatal

val e: Either[String, Int] = Right(10)
val el: Either[String, Int] = Left("FUCK")

val te: Either[String, Int] = e
  .flatMap(s => Try(s / 0).toEither.left.map(_.getMessage))



el.flatMap(ee => Right(ee * 2)
  .flatMap(ree => Try(ree * 1)
    .toEither.left.map(_.getMessage).map(mu => mu)))

//val a1: Option[Int] = Option(10)
//val multiple: Option[Int] = Option(10)

//val result: Option[String] = a1.flatMap(a => Option(a * multiple.get)).flatMap(s => Option("Hello " + s.toString))



//List(1,2,3).flatMap(i => List(i * 2))
//List(1,2,3).map(_ * 2)