package aoc2017

import io.Source

object Driver extends App {
  //val source = Source.fromFile("src/main/resources/input-day1.txt").map(_.asDigit).toSeq
  //println(s"Day1 a: ${Day1.solution1(source)}")
  //println(s"Day1 b: ${Day1.solution2(source)}")

//  val source = Source.fromFile("src/main/resources/input-day2.txt").getLines.map(_.split("\t").map(_.toInt)).toArray
//  println(s"Day 2 a: ${Day2.solution1(source)}")
//  println(s"Day 2 b: ${Day2.solution2(source)}")

  val day3Input = 277678
  println(s"Day 3 a: ${Day3.solution1(day3Input)}")

}
