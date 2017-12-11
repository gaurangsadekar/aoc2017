package aoc2017

object Day10 {
  def solution1(lengths: Seq[Int], maxNum: Int) = {
    def knotHash(xs: Seq[Int], lengths: Seq[Int], currentPos: Int, skip: Int): Seq[Int] = {
      if (lengths.isEmpty) xs
      else {
        val length = lengths.head
        val reverseIndices = (0 until length).map(i => (i + currentPos) % maxNum)
        val reversedNums = reverseIndices.collect(xs).reverse
        val rotated = reverseIndices.zip(reversedNums).foldLeft(xs){ case (list, (idx, current)) =>
          list.updated(idx, current)
        }
        knotHash(rotated, lengths.tail, (currentPos + length + skip) % maxNum, skip + 1)
      }
    }
    knotHash(0 until maxNum, lengths, 0, 0).take(2).product
  }

  def solution2(xs: Seq[Int]) = {

  }
}
