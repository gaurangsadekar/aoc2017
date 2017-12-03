package aoc2017

object Day3 {
  // total time O(lgn), no extra space
  def solution1(num: Int): Int = {
    val maxDist = findNearestSquare(num, 1)
    manhattanDistance(num, maxDist * maxDist, maxDist, maxDist - 1)
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
  def manhattanDistance(numTarget: Int, maxNum: Int, nearestSquare: Int, maxPosition: Int) = {
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
}
