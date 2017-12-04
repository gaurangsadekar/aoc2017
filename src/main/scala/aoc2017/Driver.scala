package aoc2017

import io.Source

object Driver extends App {
  //val source = Source.fromFile("src/main/resources/input-day1.txt").map(_.asDigit).toSeq
  //println(s"Day1 a: ${Day1.solution1(source)}")
  //println(s"Day1 b: ${Day1.solution2(source)}")

//  val source = Source.fromFile("src/main/resources/input-day2.txt").getLines.map(_.split("\t").map(_.toInt)).toArray
//  println(s"Day 2 a: ${Day2.solution1(source)}")
//  println(s"Day 2 b: ${Day2.solution2(source)}")

//  val day3Input = 277678
//  println(s"Day 3 a: ${Day3.solution1(day3Input)}")
//  println(s"Day 3 b: ${Day3.solution2(day3Input)}")

  val day4Input = Source.fromFile("src/main/resources/input-day4.txt").getLines().toSeq
  println(s"Day 4 a: ${Day4.solution1(day4Input)}")
  println(s"Day 4 b: ${Day4.solution2(day4Input)}")
}
