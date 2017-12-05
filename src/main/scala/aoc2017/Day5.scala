package aoc2017

object Day5 {
  private def solution(maze: Array[Int], offsetUpdate: Int => Int): Int = {
    def stepThroughMaze(i: Int, steps: Int): Int = {
      if (i >= maze.length) steps
      else {
        val jump = maze(i)
        maze(i) = offsetUpdate(jump)
        stepThroughMaze(i + jump, steps + 1)
      }
    }
    stepThroughMaze(0, 0)
  }

  def solution1(maze: Array[Int]) = {
    solution(maze, _ + 1)
  }

  def solution2(maze: Array[Int]) = {
    def offsetUpdate(jump: Int) = jump + (if (jump >= 3) -1 else 1)
    solution(maze, offsetUpdate)
  }
}
