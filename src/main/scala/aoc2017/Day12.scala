package aoc2017

object Day12 {

  type Vertex = Int
  type Edges = Seq[Vertex]
  type Graph = Map[Vertex, Edges]

  def parseAdjacencyList(strs: Seq[String]): Graph = {
    val nodeRegex = """(\d+) <-> (\d+(?:, \d+)*)""".r
    strs.map {
      case nodeRegex(nodeIdStr, childrenStr) =>
        nodeIdStr.toInt -> childrenStr.split(", ").map(_.toInt).toSeq
    }(collection.breakOut)
  }

  def connectedComponent(graph: Graph)(component: Set[Vertex], currentVertex: Vertex): Set[Vertex] = {
    graph(currentVertex).foldLeft(component) { case (cc, vertex) =>
      if (!cc(vertex))
        connectedComponent(graph)(cc + vertex, vertex)
      else cc
    }
  }


  val emptyVertexSet = Set.empty[Vertex]

  def solution1(graph: Graph): Int = {
    connectedComponent(graph)(emptyVertexSet, 0).size
  }

  def solution2(graph: Graph) = {
    graph.keys.foldLeft(emptyVertexSet -> 0){ case ((visited, numComponents), vertex) =>
      val (currentConnectedComponent, newComponent) = if (!visited(vertex))
        connectedComponent(graph)(emptyVertexSet, vertex) -> 1
      else emptyVertexSet -> 0
      (visited ++ currentConnectedComponent) -> (numComponents + newComponent)
    }._2
  }
}