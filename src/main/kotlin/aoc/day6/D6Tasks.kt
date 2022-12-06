package main.kotlin.aoc.day6

import util.FileHandler

class D6Tasks(private val line: String) {
    fun findNoOfDistinctCharactersFromStart(noOfChars: Int): Int {
        val mostRecent = ArrayDeque<Char>()

        repeat(noOfChars) {
            mostRecent.add(line[it])
        }

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

    //Task A
    d6Tasks.findNoOfDistinctCharactersFromStart(4).also { println(it) }
    //Task B
    d6Tasks.findNoOfDistinctCharactersFromStart(14).also { println(it) }
}