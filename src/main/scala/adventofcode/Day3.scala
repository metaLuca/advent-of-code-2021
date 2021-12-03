package adventofcode

import adventofcode.AdventOfCode2021.{parseInput, resultToString}
import cats.implicits.{catsSyntaxTuple2Semigroupal, toBifunctorOps, toFoldableOps}

object Day3 {
  sealed trait Bit
  case class One() extends Bit
  case class Zero() extends Bit

  case class Binary(bits: List[Bit]) {
    override def toString() = (bits map {
      case Zero() => '0'
      case One() =>  '1'
    }).mkString
  }

  def parseDiagnostics(lines: LazyList[String]): LazyList[Binary] = lines.map(parseLine)

  def parseLine(x: String): Binary = {
    val bins = x.map({
      case '0' => Zero()
      case '1' => One()
      case _ => Zero() //todo: handle effect
    })
    Binary(bins.toList)
  }

  case class BitOccurrence(zero: Int, one: Int) {
    def +(occ: BitOccurrence): BitOccurrence = BitOccurrence(zero+occ.zero, one+occ.one)
    def mostUsed: Bit = if(zero > one) Zero() else One()
    def leastUsed: Bit = if(zero < one) Zero() else One()
  }
  case class BitsUsage(occurrences: List[BitOccurrence]){
    def +(bitsUsage: BitsUsage): BitsUsage =
      BitsUsage(occurrences.lazyZip(bitsUsage.occurrences).map(_ + _))

    def toGammaRate: Binary = Binary(occurrences.map(_.mostUsed))
    def toEpsilonRate: Binary = Binary(occurrences.map(_.leastUsed))
  }
  object BitsUsage {
    def apply(): BitsUsage = BitsUsage(List.fill(12)(BitOccurrence(0, 0)))
  }

  def binToDec(binary: Binary): Int = Integer.parseInt(binary.toString(), 2)

  def countBitUsages(binary: Binary): BitsUsage = BitsUsage(binary.bits.map({
    case One() => BitOccurrence(0,1)
    case Zero() => BitOccurrence(1,0)
  }))

  def countUsages(binaries: LazyList[Binary]): BitsUsage = binaries match {
    case #::(head, next) => next.foldLeft(countBitUsages(head))((usages, binary) => usages + countBitUsages(binary))
    case _ => BitsUsage()
  }

  def calculatePowerConsumption(gammaRate: Binary, epsilonRate: Binary): Int = List(gammaRate, epsilonRate).map(binToDec).product

  def main(args: Array[String]): Unit = {
    val bitsUsage = parseInput("day3.txt")(parseDiagnostics).map(countUsages)
    val gammaRate = bitsUsage.map(_.toGammaRate)
    val epsilonRate = bitsUsage.map(_.toEpsilonRate)
    val powerConsumption = (gammaRate, epsilonRate).mapN(calculatePowerConsumption)
    println(resultToString(powerConsumption))
  }
}
