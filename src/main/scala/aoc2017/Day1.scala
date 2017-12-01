package aoc2017

object Day1 {
  // instead of passing the rotated Seq, we can pass the offset to rotate it by
  // for the 1st case the offset is 1, for the second it is length / 2
  // sum takes an implicit numeric of type B >: A. Since Long >: Int, the compiler is happy
  private def solution(xs: Seq[Int], offset: Int): Long = {
    val toZip = xs.drop(offset) ++ xs.take(offset)
    xs.zip(toZip).filter(tup => tup._1 == tup._2).map(_._1).sum
  }
  def solution1(xs: Seq[Int]): Long = {
    solution(xs, 1)
  }

  def solution2(xs: Seq[Int]): Long = {
    solution(xs, xs.length / 2)
  }
}

