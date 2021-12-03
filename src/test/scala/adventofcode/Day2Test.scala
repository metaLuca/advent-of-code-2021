package adventofcode

import _root_.adventofcode.Day2._

class Day2Test extends munit.FunSuite {

  test("parse commands") {
    val input: LazyList[String] = LazyList("down 5", "forward 8", "up 3")

    val result = parseMoveCommands(input)

    assertEquals(result, LazyList(Down(5), Forward(8), Up(3)))
  }

  test("Move forward 5") {
    val position = Submarine(Position(0, 0))

    val result = move(position, Forward(5))

    assertEquals(result, Submarine(Position(5, 0)))
  }

  test("Move down") {
    val position = Submarine(Position(5, 0))

    val result = move(position, Down(5))

    assertEquals(result, Submarine(Position(5, 5)))
  }

  test("Move up") {
    val position = Submarine(Position(8, 5))

    val result = move(position, Up(3))

    assertEquals(result, Submarine(Position(8, 2)))
  }

  test("Multiply Position 2,3") {
    val position = Position(2, 3)

    val result = multiplyPosition(position)

    assertEquals(result, 6)
  }

  test("calculate plan") {
    val commands = Option(LazyList("forward 5", "down 5", "forward 8", "up 3", "down 8", "forward 2"))

    val result: Option[Mission] = commands.map(parseMoveCommands)
      .map(Mission(Submarine(Position(0, 0)), _))
      .map(executeMission)

    assertEquals(result, Some(Mission(Submarine(Position(15,10)), LazyList.empty)))
  }
}