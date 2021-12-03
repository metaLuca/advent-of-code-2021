package adventofcode

import _root_.adventofcode.Day2_2._

class Day2Test_2 extends munit.FunSuite {

  test("parse commands") {
    val input: LazyList[String] = LazyList("down 5", "forward 8", "up 3")

    val result = parseMoveCommands(input)

    assertEquals(result, LazyList(Down(5), Forward(8), Up(3)))
  }

  test("Move forward 5") {
    val position = Submarine(Position(0, 0, 0))

    val result = move(position, Forward(5))

    assertEquals(result, Submarine(Position(5, 0, 0)))
  }

  test("Move down") {
    val position = Submarine(Position(5, 0, 0))

    val result = move(position, Down(5))

    assertEquals(result, Submarine(Position(5, 0, 5)))
  }

  test("Move up") {
    val position = Submarine(Position(13, 40, 5))

    val result = move(position, Up(3))

    assertEquals(result, Submarine(Position(13, 40, 2)))
  }

  test("Multiply Position 2,3") {
    val position = Position(15, 60, 10)

    val result = multiplyPosition(position)

    assertEquals(result, 900)
  }

  test("calculate plan") {
    val commands = Option(LazyList("forward 5", "down 5", "forward 8", "up 3", "down 8", "forward 2"))

    val result: Option[Mission] = commands.map(parseMoveCommands)
      .map(Mission(Submarine(Position(0, 0, 0)), _))
      .map(executeMission)

    assertEquals(result, Some(Mission(Submarine(Position(15,60,10)), LazyList.empty)))
  }
}