package main.kotlin.aoc.day12

import util.FileHandler

class D12Tasks {
    fun findPathSteps(input: List<String>) {
        val parser = HeightMapParser().parseHeightMap(input)

    }
}

fun main(){
    val sample = D12Tasks().findPathSteps(FileHandler.getLinesFromFile("day12Sample.txt"))

}