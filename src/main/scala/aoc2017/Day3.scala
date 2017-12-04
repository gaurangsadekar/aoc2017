package aoc2017

import collection.mutable

object Day3 {
  // total time O(lgn), no extra space
  def solution1(num: Int): Int = {
    val maxDist = findNearestSquare(num, 1)
    manhattanDistance(num, maxDist, maxDist - 1)
  }

  /*
    after finding the nearest square,
    the Manhattan distance for any point is [previous square - 1, current square - 1]
   */
  def findNearestSquare(num: Int, current: Int): Int = {
    if (current * current >= num) current
    else findNearestSquare(num, current + 2)
  }

  /*
    go through numbers from prevSquare to maxSquare (meaning numbers only in the outer grid of the spiral)
    and compute the Manhattan distance based on the position in the grid
   */
  def manhattanDistance(numTarget: Int, nearestSquare: Int, maxPosition: Int) = {
    def getManhattanDistance(numCurrent: Int, currentDistance: Int, pos: Int): Int = {
      if (numTarget == numCurrent) currentDistance
      else {
        val nextPos = if (pos == maxPosition) 1 else pos + 1
        val newDistance = currentDistance + (if (nextPos <= maxPosition / 2) -1 else 1)
        getManhattanDistance(numCurrent + 1, newDistance, nextPos)
      }
    }

    val prevMaxDist = nearestSquare - 2
    getManhattanDistance(prevMaxDist * prevMaxDist + 1, prevMaxDist, 1)
  }


  def solution2(target: Int) = {
    def spiral(grid: Grid, prev: Coordinate, direction: Coordinate, maxPos: Int): Int = {
      val (current, nextMaxPos) = if (grid.isBottomRightCorner(prev, maxPos))
        prev.move(Coordinate(1, 0)) -> (maxPos + 1) // add another spiral. Adds another coordinate depth
      else
        prev.move(direction) -> maxPos // chug along

      val nextDirection = if (grid.isOutOfBounds(current.move(direction), nextMaxPos)) {
        grid.nextCounterClockWiseDirection(direction) // turn direction in the same spiral
      } else direction

      val currentSum = grid.coordinateSum(current)
      if (currentSum > target) currentSum
      else {
        grid.state += (current -> currentSum)
        spiral(grid, current, nextDirection, nextMaxPos)
      }
    }

    spiral(new Grid(), Coordinate(0, 0), Coordinate(1, 0), 0)
  }

  class Grid {
    val state = mutable.Map(Coordinate(0, 0) -> 1)

    def isOutOfBounds(coordinate: Coordinate, maxPos: Int) = {
      Math.max(Math.abs(coordinate.x), Math.abs(coordinate.y)) > maxPos
    }

    def isBottomRightCorner(coordinate: Coordinate, maxPos: Int) = {
      coordinate.x == maxPos && coordinate.y == -maxPos
    }

    def nextCounterClockWiseDirection(direction: Coordinate) = Coordinate(-direction.y, direction.x)

    val neighbors = Seq(
      Coordinate(1, 0),
      Coordinate(1, 1),
      Coordinate(0, 1),
      Coordinate(-1, 1),
      Coordinate(-1, 0),
      Coordinate(-1, -1),
      Coordinate(0, -1),
      Coordinate(1, -1)
    )

    def coordinateSum(coordinate: Coordinate) = {
      neighbors.map(coordinate.move).flatMap(state.get).sum
    }
  }

  case class Coordinate(x: Int, y: Int) {
    def move(direction: Coordinate) = {
      Coordinate(x + direction.x, y + direction.y)
    }
  }
}
