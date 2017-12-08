package aoc2017

case class Condition(
  regName: String,
  fn: Int => Boolean
) {
  def apply(registerMap: Map[String, Int]): Boolean = {
    fn(registerMap.getOrElse(regName, 0))
  }
}

object Condition {
  def apply(regName: String, operatorStr: String, operand: Int): Condition = {
    Condition(regName,
      operatorStr match {
      case "<"  => _ < operand
      case ">"  => _ > operand
      case "<=" => _ <= operand
      case ">=" => _ >= operand
      case "==" => _ == operand
      case "!=" => _ != operand
    })
  }
}

case class Instruction(
  regName: String,
  offsetFn: Int => Int,
  condition: Condition
) {
  def apply(registerMap: Map[String, Int]): Option[Int] = {
    if (condition(registerMap))
      Some(offsetFn(registerMap.getOrElse(regName, 0)))
    else None
  }
}

object Instruction {
  def apply(
    regName: String,
    incOrDec: String,
    offset: String,
    condRegName: String,
    operatorStr: String,
    operandStr: String
  ): Instruction = {
    Instruction(
      regName,
      _ + offsetMultiplier(incOrDec) * offset.toInt,
      Condition(condRegName, operatorStr, operandStr.toInt)
    )
  }

  def offsetMultiplier(str: String) = str match {
    case "inc" => 1
    case "dec" => -1
  }
}

object Day8 {

  def parseFile(instructionStrs: Seq[String]): Seq[Instruction] = instructionStrs.map(_.split(" ") match {
    case Array(regName, incOrDec, offset, ifWord, condRegName, operatorStr, operandStr) =>
      Instruction(regName, incOrDec, offset, condRegName, operatorStr, operandStr)
  })

  def solution1(instructionStrs: Seq[String]): Int = {
    parseFile(instructionStrs)
      .foldLeft(Map.empty[String, Int]){ case (registerMap, instruction) =>
        instruction(registerMap).fold(registerMap)(value => registerMap + (instruction.regName -> value))
      }.values.max
  }

  def solution2(instructionStrs: Seq[String]): Int = {
    parseFile(instructionStrs)
      .foldLeft((Map.empty[String, Int], Int.MinValue)) { case ((registerMap, max), instruction) =>
        instruction(registerMap) match {
          case None => registerMap -> max
          case Some(value) => (registerMap + (instruction.regName -> value)) -> Math.max(max, value)
        }
      }._2
  }
}

