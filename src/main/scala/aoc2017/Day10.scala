package aoc2017

object Day10 {
  case class KnotHashParam(
    nums: Seq[Int],
    currentPos: Int,
    skip: Int
  )

  val initKnotHashParam = KnotHashParam((0 until 256), 0, 0)

  def knotHash(lengths: Seq[Int], knotHashParam: KnotHashParam): KnotHashParam = {
    if (lengths.isEmpty) knotHashParam
    else {
      val length = lengths.head
      val reverseIndices = (0 until length).map(i => (i + knotHashParam.currentPos) % 256)
      val reversedNums = reverseIndices.collect(knotHashParam.nums).reverse
      val rotated = reverseIndices.zip(reversedNums).foldLeft(knotHashParam.nums){ case (list, (idx, current)) =>
        list.updated(idx, current)
      }
      knotHash(
        lengths.tail,
        KnotHashParam(rotated, (knotHashParam.currentPos + length + knotHashParam.skip) % 256, knotHashParam.skip + 1)
      )
    }
  }

  def solution1(lengths: Seq[Int]) = {
    knotHash(lengths, initKnotHashParam).nums.take(2).product
  }

  def fullKnotHash(lengths: Seq[Int]) = {
    val sparseHash = (0 until 64).foldLeft(initKnotHashParam) { case (knotHashParam, _) =>
      knotHash(lengths, knotHashParam)
    }
    val denseHash = sparseHash.nums.grouped(16).map(_.reduceLeft(_ ^ _))
    denseHash
  }

  def solution2(lengths: Seq[Int]) = {
    fullKnotHash(lengths)
      .map(i => Integer.toString(i, 16))
      .map(i => if (i.length == 1) s"0$i" else i)
      .reduceLeft(_ + _)
  }

  def convertToKnotHashInput(key: String) = {
    key.toSeq.map(_.toInt) ++ Seq(17, 31, 73, 47, 23)
  }
}
