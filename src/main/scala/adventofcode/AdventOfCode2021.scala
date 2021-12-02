package adventofcode

import cats.implicits.toBifunctorOps

import scala.io.Source._
import scala.util.Try

object AdventOfCode2021 {
  trait Error
  case class ReadError(e: Throwable) extends Error

  def parseInput[A](textFileName: String)(converter: LazyList[String] => A): Either[Error, A] =
    Try(fromFile(s"src/main/resources/${textFileName}").getLines())
      .toEither
      .bimap(ReadError, _.to(LazyList))
      .map(converter)

  def resultToString[A](result: Either[Error, A]): String = result match {
    case Left(value) => s"Error: ${value.getClass}"
    case Right(value) => s"Result: ${value}"
  }
}
