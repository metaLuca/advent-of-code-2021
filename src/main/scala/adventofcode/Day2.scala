package adventofcode

import adventofcode.AdventOfCode2021.{parseInput, resultToString}
import adventofcode.Day1.{countDepthIncrement, parseSonarSweeps}

//import adventofcode.AdventOfCode2021.{parseInput, resultToString}

object Day2 {
  case class Mission(submarine: Submarine, plan: LazyList[MoveCommand])

  case class Submarine(position: Position)
  case class Position(horizontal: Int, depth: Int)

  sealed trait MoveCommand
  case class Up(by: Int) extends MoveCommand
  case class Down(by: Int) extends MoveCommand
  case class Forward(by: Int) extends MoveCommand
  case class Unknown() extends MoveCommand


  def parseCommand(input: String): MoveCommand = input.split(" ") match {
    case Array("up", unit, _*) => Up(unit.toInt)
    case Array("down", unit, _*) => Down(unit.toInt)
    case Array("forward", unit, _*) => Forward(unit.toInt)
    case _ => Unknown()
  }

  def parseMoveCommands(lines: LazyList[String]): LazyList[MoveCommand] = lines.map(parseCommand)

  def move(submarine: Submarine, command: MoveCommand): Submarine = {
    val position = submarine.position
    Submarine(command match {
      case Up(by) => position.copy(depth = position.depth - by)
      case Down(by) => position.copy(depth = position.depth + by)
      case Forward(by) => position.copy(horizontal = position.horizontal + by)
      case Unknown() => position
    })
  }

  def multiplyPosition(position: Position): Int = position.horizontal * position.depth

  def executeMission(mission: Mission): Mission = mission.plan match {
    case x if x.sizeIs == 0 => mission
    case head #:: tail => executeMission(Mission(move(mission.submarine, head), tail))
    case _ => mission
  }

  def main(args: Array[String]): Unit = {
    val finalPosition = parseInput("day2.txt")(parseMoveCommands)
      .map(Mission(Submarine(Position(0, 0)), _))
      .map(executeMission)
      .map(x => multiplyPosition(x.submarine.position))
    println(resultToString(finalPosition))
  }
}
