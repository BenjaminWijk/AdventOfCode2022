package main.kotlin.aoc.day11

import main.kotlin.aoc.day11.Monkey.Operation.Operand.ADD
import main.kotlin.aoc.day11.Monkey.Operation.Operand.MULTIPLY
import main.kotlin.aoc.day11.MonkeyParser.Regexes.startingItems
import main.kotlin.aoc.day11.MonkeyParser.Regexes.throwToOnFalse
import main.kotlin.aoc.day11.MonkeyParser.Regexes.throwToOnTrue
import main.kotlin.util.group
import main.kotlin.util.groups
import java.math.BigInteger

class MonkeyParser(val lines: List<String>) {

    object Regexes {
        val startingItems = ".*Starting items:.*".toRegex()
        val test = "Test: divisible by (\\d+)".toRegex()
        val operation = "Operation: new = (\\S+) (.) (\\S+)".toRegex()
        val throwToOnTrue = "If true:.*monkey (\\d+)".toRegex()
        val throwToOnFalse = "If false:.*monkey (\\d+)".toRegex()
    }


    //More optimized approach would be to just check line index. Buut regex is more fun and adaptable. :))
    fun synthesizeMonkeys(): List<Monkey> {
        val list = mutableListOf<Monkey>()

        var items: MutableList<BigInteger> = mutableListOf()
        var operation: Monkey.Operation? = null
        var test: (BigInteger) -> Boolean = { false }
        var throwOnTrue = -1

        fun Regex.groupToInt(input: String, groupNo: Int) = group(input, groupNo)?.toInt()

        lines.forEach { line ->

            if (line.matches(startingItems)) {
                val itemString = line.split("items: ")[1]
                items = itemString.split(",").map { it.trim().toBigInteger() } as MutableList<BigInteger>
            }

            Regexes.operation.groups(line, 1, 2, 3)?.let { groups ->
                val operand = groups[1].let {
                    if (it == "*") MULTIPLY else ADD
                }

                fun getOperandNumber(str: String): Monkey.Operation.OperationNumber {
                    return if (str == "old")
                        Monkey.Operation.OperationNumber(
                            true, null
                        ) else
                        Monkey.Operation.OperationNumber(false, str.toBigInteger())
                }

                operation = Monkey.Operation(operand, getOperandNumber(groups[0]), getOperandNumber(groups[2]))
            }

            Regexes.test.groupToInt(line, 1)?.let {
                test = { bigBoi -> bigBoi.divideAndRemainder(it.toBigInteger())[1].equals(BigInteger.ZERO) }
            }

            throwToOnTrue.groupToInt(line, 1)?.let { throwOnTrue = it }
            throwToOnFalse.groupToInt(line, 1)?.let {throwOnFalse ->
                //all info gathered, build monkey
                list.add(
                    Monkey(items, operation!!, test, throwOnTrue, throwOnFalse)
                )
            }
        }

        return list
    }


}