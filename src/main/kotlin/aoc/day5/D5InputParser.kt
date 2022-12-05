package main.kotlin.aoc.day5

import java.util.Stack

class D5InputParser(val lines: List<String>) {

    fun originalStackList(): List<Stack<Char>> {
        val bottomLineReg = "^[1-9\\s]*$".toRegex()
        val lastDigitReg = "(\\d+)\\s*$".toRegex()

        val bottomLineIndex = lines.indexOfFirst { it.matches(bottomLineReg) }

        val noOfColumns = lastDigitReg.find(lines[bottomLineIndex])?.groups?.last()?.value?.toInt()
            ?: throw Exception("messed up search")

        val stacks = mutableListOf<Stack<Char>>()
        repeat(noOfColumns) { stacks.add(Stack()) }

        //Build from the bottom up
        for (row in bottomLineIndex - 1 downTo 0) {
            for (col in 0 until noOfColumns) {
                //Each column causes 4 chars to be added to length, 2nd contains char
                val colIdxWithChar = col * 4 + 1

                //My util parser trims the strings, stop after last value in string
                if (colIdxWithChar >= lines[row].length) break

                lines[row][colIdxWithChar].takeIf { it != ' ' }?.let {
                    stacks[col].add(it)
                }
            }
        }

        return stacks
    }



    fun parseMoveInstructions(): List<Instruction> {
        val startIndex = lines.indexOfFirst { it.startsWith("move") }
        val instructionRegex = "move (\\d+) from (\\d+) to (\\d+)".toRegex()
        val instructions = mutableListOf<Instruction>()

        lines.subList(startIndex, lines.size).forEach {
            instructionRegex.find(it)?.groups?.let {
                fun intOf(groupPos: Int) = it[groupPos]!!.value.toInt()

                instructions.add(
                    Instruction(
                        //-1 to adjust for 0-index start
                        intOf(1), intOf(2)-1, intOf(3)-1
                    )
                )
            }
        }

        return instructions
    }


}