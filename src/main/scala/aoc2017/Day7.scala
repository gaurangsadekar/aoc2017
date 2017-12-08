package aoc2017

import scala.annotation.tailrec
import util.matching.Regex

case class Node(
  id: String,
  weight: Int,
  childrenIds: Seq[String] = Seq.empty
)

object Day7 {

  val ARROW = "->"
  val nodeRegex = raw"(\w+) \((\d+)\)".r
  val nodeWithChildrenRegex = """(\w+) \((\d+)\)(?: -> (\w+(?:, \w+)*))?""".r

  def parseGraph2(nodeStrs: Seq[String]): Map[String, Node] = {
    nodeStrs.map {
      case nodeWithChildrenRegex(nodeId, weightStr, nullableChildrenStr) =>
        Node(
          nodeId,
          weightStr.toInt,
          Option(nullableChildrenStr)
        )
    }
  }

  def parseGraph(nodeStrs: Seq[String]): Map[String, Node] = {
    val (nodesWithChildren, nodesWithoutChildren) = nodeStrs.partition(_.contains(ARROW))

    val nodesByNodeIdWithoutChildren: Map[String, Node] = nodesWithoutChildren.map {
      case nodeRegex(nodeId, weight) => nodeId -> Node(nodeId, weight.toInt)
    }(collection.breakOut)

    nodesWithChildren.foldLeft(nodesByNodeIdWithoutChildren) { case (nodesByNodeId, str) =>
      val (parentStr, childrenStr) = str.splitAt(str.indexOfSlice(ARROW))
      val (nodeId, weight) = parentStr.trim match {
        case nodeRegex(nodeId, weightStr) => nodeId -> weightStr.toInt
      }
      val children = childrenStr.substring(ARROW.length()).split(",").map(_.trim)
      nodesByNodeId + (nodeId -> Node(nodeId, weight, children))
    }
  }

  def parseParentsByChildId(nodeStrs: Seq[String]): Map[String, String] = {
    nodeStrs
      .filter(_.contains(ARROW))
      .flatMap { str =>
        val (parentStr, childrenStr) = str.splitAt(str.indexOfSlice(ARROW))
        val (nodeId, _) = parentStr.trim match {
          case nodeRegex(nodeId, weightStr) => nodeId -> weightStr.toInt
        }
        childrenStr.substring(ARROW.length()).split(",")
          .map(_.trim)
          .map(_ -> nodeId)
      }(collection.breakOut)
  }


  def solution1(nodeStrs: Seq[String]) = {
    @tailrec
    def traverse(graph: Map[String, String], nodeId: String): String = graph.get(nodeId) match {
        case None => nodeId
        case Some(parentId) => traverse(graph, parentId)
      }
    val parentsByChildId = parseParentsByChildId(nodeStrs)
    traverse(parentsByChildId, parentsByChildId.head._1)
  }

  def solution2(nodeStrs: Seq[String]) = {
    val nodesByNodeId = parseGraph(nodeStrs)
    val root = nodesByNodeId(solution1(nodeStrs))

    computeWeightUnderneath(nodesByNodeId, root)
  }

  def computeWeightUnderneath(nodesByNodeId: Map[String, Node], root: Node): Int = {
    if (root.childrenIds.isEmpty) root.weight
    else {
      val childWeights = root.childrenIds.flatMap(nodesByNodeId.get).map { child =>
        child -> computeWeightUnderneath(nodesByNodeId, child)
      }

      val (allSame, oneDifferent) = childWeights.groupBy(_._2).partition(_._2.size > 1)
      val delta = oneDifferent.headOption.map { case (weightSum, childWeights) =>
        val (child, _) = childWeights.head
        child.weight + (allSame.head._1 - weightSum)
      }

      delta.foreach(println)
      root.weight + delta.getOrElse(0) + childWeights.map(_._2).sum
    }
  }
}