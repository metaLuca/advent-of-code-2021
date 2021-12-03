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

  @tailrec
  def window3(sweeps: LazyList[SonarSweep], acc: LazyList[SonarSweep] = LazyList.empty): LazyList[SonarSweep] = sweeps match {
    case list if list.sizeIs == 3 => acc.appended(list.sum)
    case first #:: second #:: third #:: rest => window3(second #:: third #:: rest, acc.appended(first+second+third))
    case _ => acc
  }

  def main(args: Array[String]): Unit = {
    val sonar: Either[Error, Sonar] = parseInput("day1.txt")(parseSonarSweeps)
    val depthIncrements = sonar.map(countDepthIncrement(_))
    val depthIncrementsWindow3 = sonar.map(x => Sonar(window3(x.sweeps))).map(countDepthIncrement(_))
    println(resultToString(depthIncrements))
    println(resultToString(depthIncrementsWindow3))
  }
}
