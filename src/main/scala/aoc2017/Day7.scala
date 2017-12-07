package aoc2017

import util.matching.Regex

//case class Node(
//  id: String,
//  weight: Int,
//  children: Seq[Node],
//  parent: Option[Node]
//)
//
//object Node {
//  def parseStrWithoutChildren(str: String): Node = {
//
//  }
//
//  def parseStrWithChildren(str: String) = {
//    val (dataStr, childrenStr) = str.splitAt(str.indexOfSlice("->"))
//    val node = parseStrWithoutChildren(dataStr.trim)
//
//  }
//}


object Day7 {

  val ARROW = "->"

  def parseFile(nodeStrs: Seq[String]) = {
    val (nodesWithChildren, nodesWithoutChildren) = nodeStrs.partition(_.contains(ARROW))
    val nodeRegex = raw"(\w+)\s\((\d+)\)".r

    val nodes = nodesWithoutChildren.map(_ match {
      case nodeRegex(nodeId, weight) => Node(nodeId, weight.toInt)
    })


  }

  def solution1(nodeStrs: Seq[String]) = {
    val nodeStrsWithChildren = nodeStrs.filter(_.contains(ARROW))
    nodeStrsWithChildren.flatMap { str =>
      val (parentStr, childrenStr) = str.splitAt(str.indexOfSlice(ARROW))
      val nodeData = parentStr.split("\\s+").map(_.trim)
      val parent = nodeData.head.trim
      val weight =
      val children = childrenStr.substring(ARROW.length()).split(",").map(_.trim)
      ???
    }
  }
}

case class Node(
  id: String,
  weight: Int,
  childrenIds: Seq[String] = Seq.empty,
  parentId: Option[String] = None
) {

}