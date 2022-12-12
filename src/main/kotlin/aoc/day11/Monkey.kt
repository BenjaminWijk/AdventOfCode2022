package main.kotlin.aoc.day11

import java.math.BigInteger

class Monkey(
    private val items: MutableList<BigInteger>,
    private val operation: Operation,
    private val test: (BigInteger) -> Boolean,
    private val throwToWhenTrue: Int,
    private val throwToWhenFalse: Int
) {
    var monkeyBusinessCounter = 0L

    /**
     * @return was an item thrown?
     */
    fun inspectAndToss(monkeys: List<Monkey>, worryLevelDecreases: Boolean) {
        monkeyBusinessCounter++

        var worryLevel = items.removeFirst()

        worryLevel = operation.performOperation(worryLevel)
        if(worryLevelDecreases) worryLevel /= BigInteger.valueOf(3)

        val monkeyToThrowTo = if (test(worryLevel)) throwToWhenTrue else throwToWhenFalse
        monkeys[monkeyToThrowTo].items.add(worryLevel)
    }

    val canInspectItem: Boolean
        get() = items.isNotEmpty()

    class Operation(val operand: Operand, val first: OperationNumber, val second: OperationNumber){

        fun performOperation(selfValue: BigInteger): BigInteger{
            val first = if(first.self) selfValue else first.value!!
            val second = if(second.self) selfValue else second.value!!

            return if(operand == Operand.MULTIPLY) first * second else first + second
        }

        data class OperationNumber(val self: Boolean, val value: BigInteger?)
        enum class Operand {
            ADD,
            MULTIPLY
        }

    }
}