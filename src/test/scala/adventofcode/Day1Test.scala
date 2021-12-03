package adventofcode

import adventofcode.Day1._

class Day1Test extends munit.FunSuite {

  test("measurement never increase") {
    val input: LazyList[String] = LazyList.empty

    val result = countDepthIncrement(parseSonarSweeps(input))

    assertEquals(result, 0)
  }

  test("measurement increase 1 time") {
    val input: LazyList[String] = LazyList("199","200")

    val result = countDepthIncrement(parseSonarSweeps(input))

    assertEquals(result, 1)
  }

  test("measurement increase 7 times") {
    val input: LazyList[String] = LazyList("199","200","208","210","200","207","240","269","260","263")

    val result = countDepthIncrement(parseSonarSweeps(input))

    assertEquals(result, 7)
  }

  test("windows of 3") {
    val input: LazyList[String] = LazyList("199","200","208","210","200","207","240","269","260","263")

    val result = window3(parseSonarSweeps(input).sweeps, LazyList.empty)

    assertEquals(result, LazyList(607,618,618,617,647,716,769,792))
  }

  test("measurement increase 5 times") {
    val input: LazyList[Int] = LazyList(607,618,618,617,647,716,769,792)

    val result = countDepthIncrement(Sonar(input))

    assertEquals(result, 5)
  }
}