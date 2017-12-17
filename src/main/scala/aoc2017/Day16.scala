package aoc2017

import scala.annotation.tailrec

object Day16 {

  def parseDanceMoves(moveStrs: Seq[String]): Seq[Dance] = {
    val spinRegex = """s(\d+)""".r
    val exchangeRegex = """x(\d+)\/(\d+)""".r
    val partnerRegex = """p(\w)\/(\w)""".r
    moveStrs.map {
      case spinRegex(dist) => Spin(dist.toInt)
      case exchangeRegex(p1, p2) => Exchange(p1.toInt, p2.toInt)
      case partnerRegex(p1, p2) => Partner(p1.charAt(0), p2.charAt(0))
    }
  }

  def parseSideEffectDanceMoves(moveStrs: Seq[String]): Seq[SideEffectDance] = {
    val spinRegex = """s(\d+)""".r
    val exchangeRegex = """x(\d+)\/(\d+)""".r
    val partnerRegex = """p(\w)\/(\w)""".r
    moveStrs.map {
      case spinRegex(dist) => SideEffectSpin(dist.toInt)
      case exchangeRegex(p1, p2) => SideEffectExchange(p1.toInt, p2.toInt)
      case partnerRegex(p1, p2) => SideEffectPartner(p1.charAt(0), p2.charAt(0))
    }
  }


  val initPrograms = ('a' to 'p')

  private def solution(moves: Seq[Dance], programs: Array[Char]) = {
    moves.foldLeft(programs){ case (acc, danceMove) =>
      danceMove.move(acc)
    }
  }

  def solution1(moves: Seq[SideEffectDance]) = {
    val programs = initPrograms.toArray
    moves.foreach(_.move(programs))
    programs.mkString("")
  }

  def solution2(moves: Seq[SideEffectDance]) = {
    val programs = initPrograms.toArray
    for (i <- 0 until 1000000000) {
      if (i % 10000  == 0) println(i)
      moves.foreach(_.move(programs))
    }
    programs.mkString("")
  }
}

trait Dance {
  def move(moves: Array[Char]): Array[Char]
}

case class Spin(fromEnd: Int) extends Dance {
  def move(programs: Array[Char]): Array[Char] = {
    programs.takeRight(fromEnd) ++ programs.take(programs.size - fromEnd)
  }
}

case class Exchange(pos1: Int, pos2: Int) extends Dance {
  def move(moves: Array[Char]): Array[Char] = {
    val temp = moves(pos1)
    moves(pos1) = moves(pos2)
    moves(pos2) = temp
    moves
  }
}

case class Partner(pName1: Char, pName2: Char) extends Dance {
  def move(moves: Array[Char]): Array[Char] = {
    Exchange(moves.indexOf(pName1), moves.indexOf(pName2)).move(moves)
  }
}

trait SideEffectDance {
  def move(programs: Array[Char]): Unit
}

case class SideEffectSpin(fromEnd: Int) extends SideEffectDance {
  override def move(programs: Array[Char]): Unit = {
    val rotated = programs.takeRight(fromEnd) ++ programs.take(programs.size - fromEnd)
    rotated.zipWithIndex.foreach { case (char, pos) =>
      programs(pos) = char
    }
  }
}

case class SideEffectExchange(pos1: Int, pos2: Int) extends SideEffectDance {
  override def move(programs: Array[Char]): Unit = {
    val temp = programs(pos1)
    programs(pos1) = programs(pos2)
    programs(pos2) = temp
  }
}

case class SideEffectPartner(pName1: Char, pName2: Char) extends SideEffectDance {
  override def move(programs: Array[Char]): Unit = {
    SideEffectExchange(programs.indexOf(pName1), programs.indexOf(pName2)).move(programs)
  }
}