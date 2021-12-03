package adventofcode

import adventofcode.Day3.{Binary, BitOccurrence, BitsUsage, One, Zero, binToDec, calculatePowerConsumption, countUsages, parseDiagnostics, parseLine}

class Day3Test extends munit.FunSuite {

  test("get bit usage") {
    val input = LazyList("101")

    val result = parseDiagnostics(input)

    assertEquals(result, LazyList(Binary(List(One(), Zero(), One()))))
  }

  test("parse lines") {
    val input = LazyList(
      "000000000100",
      "000000011110",
      "000000010110",
      "000000010111",
      "000000010101",
      "000000001111",
      "000000000111",
      "000000011100",
      "000000010000",
      "000000011001",
      "000000000010",
      "000000001010"
    )
    val expected = LazyList(
      Binary(List(Zero(), Zero(), Zero(), Zero(), Zero(), Zero(), Zero(), Zero(), Zero(), One(), Zero(), Zero())),
      Binary(List(Zero(), Zero(), Zero(), Zero(), Zero(), Zero(), Zero(), One(), One(), One(), One(), Zero())),
      Binary(List(Zero(), Zero(), Zero(), Zero(), Zero(), Zero(), Zero(), One(), Zero(), One(), One(), Zero())),
      Binary(List(Zero(), Zero(), Zero(), Zero(), Zero(), Zero(), Zero(), One(), Zero(), One(), One(), One())),
      Binary(List(Zero(), Zero(), Zero(), Zero(), Zero(), Zero(), Zero(), One(), Zero(), One(), Zero(), One())),
      Binary(List(Zero(), Zero(), Zero(), Zero(), Zero(), Zero(), Zero(), Zero(), One(), One(), One(), One())),
      Binary(List(Zero(), Zero(), Zero(), Zero(), Zero(), Zero(), Zero(), Zero(), Zero(), One(), One(), One())),
      Binary(List(Zero(), Zero(), Zero(), Zero(), Zero(), Zero(), Zero(), One(), One(), One(), Zero(), Zero())),
      Binary(List(Zero(), Zero(), Zero(), Zero(), Zero(), Zero(), Zero(), One(), Zero(), Zero(), Zero(), Zero())),
      Binary(List(Zero(), Zero(), Zero(), Zero(), Zero(), Zero(), Zero(), One(), One(), Zero(), Zero(), One())),
      Binary(List(Zero(), Zero(), Zero(), Zero(), Zero(), Zero(), Zero(), Zero(), Zero(), Zero(), One(), Zero())),
      Binary(List(Zero(), Zero(), Zero(), Zero(), Zero(), Zero(), Zero(), Zero(), One(), Zero(), One(), Zero()))
    )

    val result = parseDiagnostics(input)

    assertEquals(result, expected)
  }

  test("count occurrences") {
    val input = LazyList(
      "000000000100",
      "000000011110",
      "000000010110",
      "000000010111",
      "000000010101",
      "000000001111",
      "000000000111",
      "000000011100",
      "000000010000",
      "000000011001",
      "000000000010",
      "000000001010"
    )
    val expected = BitsUsage(List(
      BitOccurrence(12, 0),
      BitOccurrence(12, 0),
      BitOccurrence(12, 0),
      BitOccurrence(12, 0),
      BitOccurrence(12, 0),
      BitOccurrence(12, 0),
      BitOccurrence(12, 0),
      BitOccurrence(5, 7),
      BitOccurrence(7, 5),
      BitOccurrence(4, 8),
      BitOccurrence(5, 7),
      BitOccurrence(7, 5),
    ))

    val result = countUsages(parseDiagnostics(input))

    assertEquals(result, expected)
  }
  test("count occurrences") {
    val input = LazyList(
      "00100",
      "11110",
      "10110",
      "10111",
      "10101",
      "01111",
      "00111",
      "11100",
      "10000",
      "11001",
      "00010",
      "01010"
    )
    val expected = BitsUsage(List(
      BitOccurrence(5, 7),
      BitOccurrence(7, 5),
      BitOccurrence(4, 8),
      BitOccurrence(5, 7),
      BitOccurrence(7, 5),
    ))

    val result = countUsages(parseDiagnostics(input))

    assertEquals(result, expected)
  }

  test("get epsilon rate from occurrences") {
    val input = BitsUsage(List(
      BitOccurrence(5, 7),
      BitOccurrence(7, 5),
      BitOccurrence(4, 8),
      BitOccurrence(5, 7),
      BitOccurrence(7, 5),
    ))
    val expected = parseLine("01001")

    assertEquals(input.toEpsilonRate, expected)
  }

  test("get gamma rate from occurrences") {
    val input = BitsUsage(List(
      BitOccurrence(5, 7),
      BitOccurrence(7, 5),
      BitOccurrence(4, 8),
      BitOccurrence(5, 7),
      BitOccurrence(7, 5),
    ))
    val expected = parseLine("10110")

    assertEquals(input.toGammaRate, expected)
  }

  test("binary 01001 to decimal 9") {
    val input = parseLine("01001")

    val result = binToDec(input)

    assertEquals(result, 9)
  }

  test("binary 10110 to decimal 22") {
    val input = parseLine("10110")

    val result = binToDec(input)

    assertEquals(result, 22)
  }


  test("calculate power consumption") {
    val epsilonRate: Binary = parseLine("01001")
    val gammaRate: Binary = parseLine("10110")

    val result = calculatePowerConsumption(gammaRate, epsilonRate)

    assertEquals(result, 198)
  }

}