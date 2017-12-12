package aoc2017

import aoc2017.Day3.Coordinate

object Day11 {
  def distance(coordinate: Coordinate) = {
    val diff = Math.abs(Math.abs(coordinate.x) - Math.abs(coordinate.y))
    // above the x = y line, it takes x steps to get to the (x, x) under the point (x, y), and diff / 2 steps to got north (because that is one step)
    if (Math.abs(coordinate.x) < Math.abs(coordinate.y)) Math.abs(coordinate.x) + diff / 2
    // below x = y, it takes y steps to get to (y, y) to the left of the point (x, y), and then the diff distance to the east (e = ne + se, which is 2 steps)
    else Math.abs(coordinate.y) + diff
  }

  def solution1(steps: Seq[String]) = {
    val finalPos = steps.foldLeft(Coordinate(0, 0)){ case (coordinate, dirStr) =>
      coordinate.move(directionStrToCoordinate(dirStr))
    }
    distance(finalPos)
  }

  def solution2(steps: Seq[String]) = {
    steps.foldLeft(List(Coordinate(0, 0))){ case (positions, dirStr) =>
      positions.head.move(directionStrToCoordinate(dirStr)) :: positions
    }.map(distance).max
  }

  val directionStrToCoordinate = Map(
    "n"   -> Coordinate(0, 2),
    "ne"  -> Coordinate(1, 1),
    "se"  -> Coordinate(1, -1),
    "s"   -> Coordinate(0, -2),
    "sw"  -> Coordinate(-1, -1),
    "nw"  -> Coordinate(-1, 1)
  )
}
