package main.kotlin.aoc.day11

import main.kotlin.aoc.day11.MonkeyParser.Regexes.monkeyPos
import main.kotlin.aoc.day11.MonkeyParser.Regexes.startingItems
import main.kotlin.aoc.day11.MonkeyParser.Regexes.throwToOnFalse
import main.kotlin.aoc.day11.MonkeyParser.Regexes.throwToOnTrue
import main.kotlin.util.group
import main.kotlin.util.groups
import util.matching
import java.math.BigInteger

class MonkeyParser(val lines: List<String>) {

    object Regexes {
        val monkeyPos = "Monkey (\\d)+:".toRegex()
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
        var test: Monkey.(BigInteger) -> Boolean = { _ -> false }
        var throwOnTrue = -1

        fun Regex.groupToInt(input: String, groupNo: Int) = group(input, groupNo)?.toInt()

        lines.forEach { line ->
            //Pos not really not necessary, I just want to cover each non-blank line.
            monkeyPos.find(line)?.groups?.get(1)?.value?.toInt()?.let {
                check(it == list.size) { "found monkey number $it, but last mapped monkey was ${list.size - 1}" }
            }

            if (line.matches(startingItems)) {
                val itemString = line.split("items: ")[1]
                items = itemString.split(",").map { it.trim().toBigInteger() } as MutableList<BigInteger>
            }

            Regexes.operation.groups(line, 1, 2, 3)?.let { groups ->
                fun getOperandNumber(str: String): Monkey.Operation.OperationNumber {
                    return if (str == "old")
                        Monkey.Operation.OperationNumber(true, null)
                    else
                        Monkey.Operation.OperationNumber(false, str.toBigInteger())
                }

                //group 1 is a single character.
                val operand = Monkey.Operation.Operand::class.matching { groups[1][0] == it.char }

                operation = Monkey.Operation(operand, getOperandNumber(groups[0]), getOperandNumber(groups[2]))
            }

            Regexes.test.groupToInt(line, 1)?.let {
                test = { bigBoi ->
                    bigBoi.remainder(it.toBigInteger()) == BigInteger.ZERO
                }
            }

            throwToOnTrue.groupToInt(line, 1)?.let { throwOnTrue = it }
            throwToOnFalse.groupToInt(line, 1)?.let { throwOnFalse ->
                //all info gathered, build monkey
                list.add(
                    Monkey(items, operation!!, test, throwOnTrue, throwOnFalse)
                )
            }
        }

        return list
    }


}