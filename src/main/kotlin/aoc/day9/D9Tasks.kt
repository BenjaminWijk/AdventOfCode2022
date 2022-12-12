package aoc.day9

import util.printIt
import util.FileHandler

class D9Tasks(val input: List<String>) {

    fun getNumberOfTailPositions(ropeLength: Int, visualise: Boolean = false): Int {

        val parsedInput = input.map {
            val direction = Direction.of(it[0])
            val repetitions = it.substring(2).toInt()
            direction to repetitions
        }
        val visualiser = if(visualise) RopeVisualiser(parsedInput) else null
        val rope = Rope(ropeLength, visualiser)

        parsedInput.forEach {
            rope.moveHead(it.first, it.second )
        }

        return rope.tailTrail.size
    }

}

fun main() {

    val d9Tasks = D9Tasks(FileHandler.getLinesFromFile("day9.txt"))

    //Task A, answer: 5513
     d9Tasks.getNumberOfTailPositions(2).printIt()
    //Task B, answer: <Not yet done>
      d9Tasks.getNumberOfTailPositions(10).printIt()
 //   val d9ASampleTask = D9Tasks(FileHandler.getLinesFromFile("day9ASample.txt"))
  //  d9ASampleTask.getNumberOfTailPositions(10).printIt("sample A result: ")

    val d9SampleTask = D9Tasks(FileHandler.getLinesFromFile("day9BSample.txt"))
    d9SampleTask.getNumberOfTailPositions(10).printIt("sample B result: ")

}