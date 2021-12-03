package adventofcode

import adventofcode.AdventOfCode2021._

import scala.annotation.tailrec

object Day1 {
  type SonarSweep = Int
  case class Sonar(sweeps: LazyList[SonarSweep])

  def parseSonarSweeps(lines: LazyList[String]): Sonar = Sonar(lines.map(_.toInt))

  @tailrec
  def countDepthIncrement(sonar: Sonar, startingFrom: Int = 0): Int = sonar.sweeps match {
    case x if x.sizeIs < 2 => startingFrom
    case first #:: rest =>
      val increment = if (first < rest.head) 1 else 0
      countDepthIncrement(Sonar(rest), increment + startingFrom)
    case _ => startingFrom
  }

  def main(args: Array[String]): Unit = {
    val depthIncrements = parseInput("day1.txt")(parseSonarSweeps).map(countDepthIncrement(_))
    println(resultToString(depthIncrements))
  }
}
