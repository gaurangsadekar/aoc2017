package aoc2017

object Day6 {
  def redistributeAndSerializeConfig(xs: Array[Int]) = {
    val (maxVal, maxIndex) = xs.zipWithIndex.maxBy(_._1)
    xs(maxIndex) = 0
    (maxVal until 0 by -1).foldLeft(maxIndex) { case (prevIdx, _) =>
      val currIdx = (prevIdx + 1) % xs.length
      xs(currIdx) += 1
      currIdx
    }
    xs -> xs.foldLeft("")(_ + "_" + _)
  }

  def solution1(xs: Array[Int]) = {
    def redistributionCycles(arr: Array[Int], uniqueConfigs: Set[String], numRedistributions: Int): Int = {
      val (redistributedArr, currentConfig) = redistributeAndSerializeConfig(arr)
      if (uniqueConfigs(currentConfig)) numRedistributions + 1
      else redistributionCycles(redistributedArr, uniqueConfigs + currentConfig, numRedistributions + 1)
    }
    redistributionCycles(xs, Set.empty, 0)
  }

  def solution2(xs: Array[Int]) = {
    def cyclesInRedistribution(arr: Array[Int], edgesInCycleByConfig: Map[String, Int]): Int = {
      val (redistributedArr, currentConfig) = redistributeAndSerializeConfig(arr)
      edgesInCycleByConfig.get(currentConfig) match {
        case Some(cycleCount) => cycleCount + 1 // current redistribution to an existing config adds an edge in the cycle
        case None => cyclesInRedistribution(
          redistributedArr,
          edgesInCycleByConfig.map { case (k, v) => k -> (v + 1) } + (currentConfig -> 0)
        ) // add the current config, but it has no edges starting from it yet
      }
    }
    cyclesInRedistribution(xs, Map.empty[String, Int])
  }
}
