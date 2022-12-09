package aoc.day9

import util.printIt
import util.FileHandler

class D9Tasks(val input: List<String>) {

    fun getNumberOfTailPositions(ropeLength: Int) : Int{
        val rope = Rope(ropeLength)
        input.forEach {
            val direction = Direction.of(it[0])
            val repetitions = it.substring(2).toInt()

            rope.moveHead(direction,repetitions)
        }

        return rope.tailTrail.size
    }

}

fun main(){

    val d9Tasks = D9Tasks(FileHandler.getLinesFromFile("day9.txt"))

    //Task A, answer: 5513
    d9Tasks.getNumberOfTailPositions(2).printIt()
    //Task B, answer: <Not yet done>
    d9Tasks.getNumberOfTailPositions(10).printIt()

    val d9SampleTask = D9Tasks(FileHandler.getLinesFromFile("day9Sample.txt"))
    d9SampleTask.getNumberOfTailPositions(10).printIt("sample result: ")

}