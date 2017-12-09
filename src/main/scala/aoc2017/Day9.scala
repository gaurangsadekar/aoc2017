package aoc2017

object Day9 {
  def solution1(input: Iterator[Char]) = {
    def countScore(current: Char, currentScore: Long, totalScore: Long, garbage: Boolean): Long = {
      val (newCurrentScore, newTotalScore, newGarbage) = if (garbage) {
        current match {
          case '!' => {
            val _ = input.next()
            (currentScore, totalScore, garbage)
          }
          case '>' =>
            (currentScore, totalScore, false)
          case _ => (currentScore, totalScore, garbage)
        }
      } else {
        current match {
          case '{' => (currentScore + 1, totalScore, garbage)
          case '}' => (currentScore - 1, totalScore + currentScore, garbage)
          case '<' => (currentScore, totalScore, true)
          case '!' => {
            val _ = input.next() // throw away immediate next char, side effect
            (currentScore, totalScore, garbage)
          }
          case _ => (currentScore, totalScore, garbage)
        }
      }
      if (!input.hasNext) newTotalScore
      else {
        countScore(input.next(), newCurrentScore, newTotalScore, newGarbage)
      }
    }
    countScore(input.next(), 0, 0, false)
  }

  def solution2(input: Iterator[Char]) = {
    def countInGarbage(current: Char, currentCount: Long, totalCount: Long, garbage: Boolean): Long = {
      val (newCurrentCount, newTotalCount, newGarbage) = if (garbage) {
        current match {
          case '!' => {
            val _ = input.next()
            (currentCount, totalCount, garbage)
          }
          case '>' =>
            (0L, totalCount + currentCount, false)
          case _ => (currentCount + 1, totalCount, garbage)
        }
      } else {
        current match {
          case '<' => (currentCount, totalCount, true)
          case '!' => {
            val _ = input.next() // throw away immediate next char, side effect
            (currentCount, totalCount, garbage)
          }
          case _ => (currentCount, totalCount, garbage)
        }
      }
      if (!input.hasNext) newTotalCount
      else {
        countInGarbage(input.next(), newCurrentCount, newTotalCount, newGarbage)
      }
    }
    countInGarbage(input.next(), 0, 0, false)
  }
}
