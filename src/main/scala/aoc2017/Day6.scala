package aoc2017

object Day6 {
  def solution1(xs: Array[Int]) = {
    def redistributionCycles(uniqueConfigs: Set[String], numCycles: Int): Int = {
      val (maxVal, maxIndex) = xs.zipWithIndex.maxBy(_._1)
      xs(maxIndex) = 0
      (maxVal until 0 by -1).foldLeft(maxIndex) { case (prevIdx, _) =>
        val currIdx = (prevIdx + 1) % xs.length
        xs(currIdx) += 1
        currIdx
      }
      val currentConfig = xs.foldLeft("")(_ + "_" + _)
      if (uniqueConfigs(currentConfig)) numCycles + 1
      else redistributionCycles(uniqueConfigs + currentConfig, numCycles + 1)
    }
    redistributionCycles(Set.empty, 0)
  }

  def solution2(xs: Array[Int]) = {
    def cyclesInRedistribution(edgesInCycleByConfig: Map[String, Int]): Int = {
      val (maxVal, maxIndex) = xs.zipWithIndex.maxBy(_._1)
      xs(maxIndex) = 0
      (maxVal until 0 by -1).foldLeft(maxIndex) { case (prevIdx, _) =>
        val currIdx = (prevIdx + 1) % xs.length
        xs(currIdx) += 1
        currIdx
      }

      val currentConfig = xs.foldLeft("")(_ + "_" + _)
      edgesInCycleByConfig.get(currentConfig) match {
        case Some(cycleCount) => cycleCount + 1
        case None => cyclesInRedistribution(edgesInCycleByConfig.map { case (k, v) => (k, v + 1) } + (currentConfig -> 0))
      }
    }
    cyclesInRedistribution(Map.empty[String, Int])
  }
}
