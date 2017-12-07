package aoc2017

case class Node(
  id: String,
  weight: Int,
  children: Seq[Node],
  parent: Option[Node]
)

object Node {
  def parseStrWithoutChildren(str: String): Node = {

  }

  def parseStrWithChildren(str: String) = {
    val (dataStr, childrenStr) = str.splitAt(str.indexOfSlice("->"))
    val node = parseStrWithoutChildren(dataStr.trim)

  }
}


object Day7 {
  def solution1() = {

  }
}
