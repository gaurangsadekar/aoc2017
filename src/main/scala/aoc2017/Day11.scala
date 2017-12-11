package aoc2017

import aoc2017.Day3.Coordinate

object Day11 {
  def solution1(steps: Seq[String]) = {
    val finalPos = steps.foldLeft(Coordinate(0, 0)){ case (coordinate, dirStr) =>
      coordinate.move(directionStrToCoordinate(dirStr))
    }
    println(finalPos)
  }

  def solution2(steps: Seq[String]) = {

  }

  val directionStrToCoordinate = Seq("e", "ne", "n", "nw", "w", "sw", "s", "se").zip(Day3.Grid.neighbors).toMap
}
