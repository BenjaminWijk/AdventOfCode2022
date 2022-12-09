package aoc.day5

import util.printIt
import util.FileHandler
import java.util.*

data class Instruction(val move: Int, val from: Int, val to: Int)

class D5Task(private val stacks: List<Stack<Char>>, private val instructions: List<Instruction>) {

    fun moveCrates9000(): String {
        instructions.forEach { ins ->
            repeat(ins.move) {
                stacks[ins.to].push(stacks[ins.from].pop())
            }
        }

        return retrieveTopStacks()
    }

    fun moveCrates9001(): String {
        instructions.forEach { ins ->
            val cratesToMove = ArrayDeque<Char>()
            repeat(ins.move){
                cratesToMove.add(stacks[ins.from].pop())
            }
            repeat(ins.move){
                stacks[ins.to].push(cratesToMove.removeLast())
            }
        }

        return retrieveTopStacks()
    }

    private fun retrieveTopStacks(): String{
        var topString = ""
        stacks.forEach { topString += it.pop() }
        return topString
    }

}

fun main() {
    val d5InputParser = D5InputParser(FileHandler.getLinesFromFile("day5.txt"))
    val instructions = d5InputParser.parseMoveInstructions()

    //recreate stack each time, since I'm modifying the stack
    //Task A, answer: FZCMJCRHZ
    D5Task(d5InputParser.originalStackList(), instructions).moveCrates9000().printIt()
    //Task B, answer: JSDHQMZGF
    D5Task(d5InputParser.originalStackList(), instructions).moveCrates9001().printIt()


}