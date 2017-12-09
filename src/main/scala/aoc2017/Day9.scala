package aoc2017

object Day9 {
  def solution1(input: Iterator[Char]) = {
    def countScore(current: Char, currentScore: Long, totalScore: Long, garbage: Boolean): Long = {
      val (newCurrentScore, newTotalScore) = if (!garbage) {
        current match {
          case '{' => (currentScore + 1, totalScore)
          case '}' => (currentScore - 1, totalScore + currentScore)
          case _ => (currentScore, totalScore)
        }
      } else (currentScore, totalScore)
      if (input.hasNext) {
        countScore(
          current = {
            if (current == '!') {
              val _ = input.next
            }
            input.next
          },
          currentScore = newCurrentScore,
          totalScore = newTotalScore,
          garbage = if (current == '<') true else if (current == '>') false else garbage
        )
      } else newTotalScore
    }
    countScore(input.next(), 0, 0, false)
  }

  def solution2(input: Iterator[Char]) = {
    def countInGarbage(current: Char, count: Long, garbage: Boolean): Long = {
      val (newCount, newGarbage) = (current, garbage) match {
        case ('!', _) => { input.next() }; (count, garbage)
        case ('>', true) => (count, false)
        case (_, true) => (count + 1, true)
        case ('<', false) => (count, true)
        case (_, _) => (count, garbage)
      }
      if (input.hasNext) countInGarbage(input.next(), newCount, newGarbage)
      else newCount
    }
    countInGarbage(input.next, 0, false)
  }
}
