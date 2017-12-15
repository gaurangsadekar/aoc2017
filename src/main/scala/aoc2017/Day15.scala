package aoc2017

import scala.annotation.tailrec

object Day15 {

  private def solution(pair: (Int, Int)) = {
    val bitMatch: Long = 1 << 16

    @tailrec
    def computeNext(current: Long, factor: Long, criteria: Option[Long]): Long = {
      val candidate = current * factor % 2147483647
      if (criteria.forall(candidate % _ == 0)) candidate
      else computeNext(candidate, factor, criteria)
    }

    @tailrec
    def countMatches(
      genA: Long,
      genB: Long,
      idx: Long,
      count: Long
    )(
      maxPairs: Long,
      criteriaA: Option[Long],
      criteriaB: Option[Long]
    ): Long = {
      if (idx == maxPairs) count
      else {
        val newGenA = computeNext(genA, 16807, criteriaA)
        val newGenB = computeNext(genB, 48271, criteriaB)
        countMatches(newGenA, newGenB, idx + 1, count + (if (newGenA % bitMatch == newGenB % bitMatch) 1 else 0))(maxPairs, criteriaA, criteriaB)
      }
    }
    countMatches(pair._1, pair._2, 0, 0) _
  }

  def solution1(pair: (Int, Int)) = {
    solution(pair)(40000000, None, None)
  }

  def solution2(pair: (Int, Int)) = {
    solution(pair)(5000000, Some(4), Some(8))
  }
}
