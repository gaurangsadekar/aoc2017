package aoc2017

object Day13 {

  def scannerPos(n: Int, time: Int) = {
    val x = time % (2 * n)
    if (x < n) x
    else 2 * n - x
  }

  def passThroughLayers(layerToRange: Map[Int, Int], delay: Int = 0): Seq[Int] = {
    (0 to layerToRange.maxBy(_._1)._1)
      .filter(t => layerToRange.get(t).exists(r => scannerPos(r - 1, t + delay) == 0))
  }

  def solution1(layerToRange: Map[Int, Int]) = {
    passThroughLayers(layerToRange)
      .map(l => l * layerToRange(l))
      .sum
  }

  def solution2(layerToRange: Map[Int, Int]): Int = {
    def minDelay(delay: Int): Int = {
      if (passThroughLayers(layerToRange, delay).isEmpty) delay
      else minDelay(delay + 1)
    }
    minDelay(0)
  }
}
