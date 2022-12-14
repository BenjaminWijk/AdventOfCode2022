package aoc.day6

import util.printIt
import util.FileHandler

class D6Tasks(private val line: String) {
    fun findNoOfDistinctCharactersFromStart(noOfChars: Int): Int {
        val mostRecent = ArrayDeque<Char>()

        repeat(noOfChars) { mostRecent.add(line[it]) }

        fun allUnique() = mostRecent.toSet().size == noOfChars

        for (c in noOfChars until line.length) {
            if (allUnique()) return c

            mostRecent.add(line[c])
            mostRecent.removeFirst()
        }

        throw Exception("nothing found")
    }
}

fun main() {
    val d6Tasks = D6Tasks(FileHandler.getLinesFromFile("day6.txt")[0])

    //Task A, answer: 1262
    d6Tasks.findNoOfDistinctCharactersFromStart(4).printIt()
    //Task B, answer: 3444
    d6Tasks.findNoOfDistinctCharactersFromStart(14).printIt()
}