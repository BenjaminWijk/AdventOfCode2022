package aoc.day3

import main.kotlin.util.printIt
import util.FileHandler
import java.lang.Exception

class D3Tasks(itemInfo: List<String>) {

    private val rucksacks = itemInfo.map { Rucksack(it) }

    private val priority = mutableMapOf<Char, Int>().apply {
        for (i in 1 until 27) {
            put((i + 96).toChar(), i) //lowerCase
            put((i + 64).toChar(), i + 26) //uppercase
        }
    }

    fun sumOfPrio(): Int {
        var totalPrio = 0
        rucksacks.forEach {
            totalPrio += priority[it.findSharedItem()] ?: 0
        }

        return totalPrio
    }

    fun sumOfBadgePrio(): Int {
        var totalPrio = 0

        for (i in 0 until rucksacks.size / 3){
            val group = rucksacks.slice(i*3 .. (i*3 + 2))
            totalPrio += priority[findSharedType(group)] ?: 0
        }

        return totalPrio
    }

    fun findSharedType(group: List<Rucksack>): Char{
        val contents = group.map { it.completeContent.toSet() }

        return priority.keys.find { char ->
            contents.all { it.contains(char) }
        } ?: throw Exception("No shared type found")
    }

}

fun main() {
    val d3Task = D3Tasks(FileHandler.getLinesFromFile("day3.txt"))
    //Task A, answer: 8139
    d3Task.sumOfPrio().printIt()
    //Task B, answer: 2668
    d3Task.sumOfBadgePrio().printIt()

}