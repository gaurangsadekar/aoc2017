package aoc2017

object Day2 {
  private def solution(rows: Array[Array[Int]])(fn: Array[Int] => Int): Long = {
    rows.map(fn).sum
  }
  def solution1(rows: Array[Array[Int]]): Long = {
    solution(rows)(row => row.max - row.min)
  }

  def solution2(rows: Array[Array[Int]]): Long = {
    solution(rows){ row =>
      def findDivisibles(head: Int, tail: Array[Int]): Int = {
        tail.collectFirst { case i if i % head == 0 => i } match {
          case Some(divisible) => divisible / head
          case None => findDivisibles(tail.head, tail.tail)
        }
      }
      val sortedRow = row.sorted
      findDivisibles(sortedRow.head, sortedRow.tail)
    }
  }

}
