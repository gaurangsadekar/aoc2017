package aoc2017

import Day10._

object Day14 {
  def solution1(key: String): Int = {
    (0 until 128).map { i =>
      val knotHash = Day10.solution2(convertToKnotHashInput(s"$key-$i"))
      BigInt(knotHash, 16).toString(2).count(_ == '1')
    }.sum
  }

  def solution2(key: String) = {
    val defrag: Array[Array[Int]] = (0 until 128).map { i =>
      val binaryKnotHash = BigInt(Day10.solution2(convertToKnotHashInput(s"$key-$i")), 16)
        .toString(2)
      "%128s".format(binaryKnotHash).replaceAll(" ", "0").map(_.asDigit).toArray
    }(collection.breakOut)

    def findGroup(x: Int, y: Int) {
      if (isInBounds(x) && isInBounds(y)) {
        if (defrag(x)(y) == 1) {
          defrag(x)(y) = 2 // mark visited
          findGroup(x - 1, y)
          findGroup(x, y - 1)
          findGroup(x + 1, y)
          findGroup(x, y + 1)
        }
      }
    }

    def isInBounds(i: Int) = {
      0 <= i && i < 128
    }

    var counter = 0
    (0 until 128).foreach { x =>
      (0 until 128).foreach { y =>
        if (defrag(x)(y) == 1) {
          counter += 1
          findGroup(x, y)
        }
      }
    }
    counter
  }
}
