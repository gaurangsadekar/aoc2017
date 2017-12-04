package aoc2017

object Day4 {
  private def solution(lines: Seq[String], criteriaConverter: Seq[String] => Seq[String]): Int = {
    lines.map(_.split("\\s+").toSeq).map(criteriaConverter).count(row => row.distinct == row)
  }

  def solution1(lines: Seq[String]) = {
    solution(lines, identity)
  }

  def solution2(lines: Seq[String]) = {
    solution(lines, row => row.map(_.toSeq.sorted.mkString))
  }
}
