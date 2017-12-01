package aoc2017

import io.Source

object Driver extends App {
  val source = Source.fromFile("src/main/resources/input-day1.txt").map(_.asDigit).toSeq
  println(s"Day1 a: ${Day1.solution1(source)}")
  println(s"Day1 b: ${Day1.solution2(source)}")
}
