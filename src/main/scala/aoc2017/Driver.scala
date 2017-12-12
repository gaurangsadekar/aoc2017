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

//  val day4Input = Source.fromFile("src/main/resources/input-day4.txt").getLines().toSeq
//  println(s"Day 4 a: ${Day4.solution1(day4Input)}")
//  println(s"Day 4 b: ${Day4.solution2(day4Input)}")

//  val day5Input = Source.fromFile("src/main/resources/input-day5.txt").getLines().toArray.map(_.toInt)
//  println(s"Day 5 a: ${Day5.solution1(day5Input)}")
//  println(s"Day 5 b: ${Day5.solution2(day5Input)}")

//  val day6Input = Source.fromFile("src/main/resources/input-day6.txt").getLines().flatMap(_.split("\t").map(_.toInt)).toArray
//  println(s"Day 6 a: ${Day6.solution1(day6Input)}")
//  println(s"Day 6 b: ${Day6.solution2(day6Input)}")

//  val day7Input = Source.fromFile("src/main/resources/input-day7.txt").getLines().toSeq
//    println(s"Day 7 a: ${Day7.solution1(day7Input)}")
//    println(s"Day 7 b: ${Day7.solution2(day7Input)}")

//  val day8Input = Source.fromFile("src/main/resources/input-day8.txt").getLines().toSeq
//  println(s"Day 8 a: ${Day8.solution1(day8Input)}")
//  println(s"Day 8 b: ${Day8.solution2(day8Input)}")

//  val day9Input = Source.fromFile("src/main/resources/input-day9.txt").getLines().next()
//  println(s"Day 9 a: ${Day9.solution1(day9Input.toIterator)}")
//  println(s"Day 9 b: ${Day9.solution2(day9Input.toIterator)}")

//  val day10AInput = Seq(147,37,249,1,31,2,226,0,161,71,254,243,183,255,30,70)
//  val day10BInput = "147,37,249,1,31,2,226,0,161,71,254,243,183,255,30,70".toSeq.map(_.toInt) ++ Seq(17, 31, 73, 47, 23)
//  println(s"Day 10 a: ${Day10.solution1(day10AInput)}")
//  println(s"Day 10 b: ${Day10.solution2(day10BInput)}")

  val day11Input = Source.fromFile("src/main/resources/input-day11.txt").getLines().next().split(",")
  println(s"Day 11 a: ${Day11.solution1(day11Input)}")
  println(s"Day 11 b: ${Day11.solution2(day11Input)}")
}
