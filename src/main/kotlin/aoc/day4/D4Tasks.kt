package aoc.day4

import util.printIt
import util.FileHandler

class D4Tasks(rawRanges: List<String>) {

    val assigmentPairs: List<AssigmentPair>

    init {
        //regex for the lulz
        val regex = "(\\d+)-(\\d+),(\\d+)-(\\d+)".toRegex()

        assigmentPairs = rawRanges.map {
            regex.find(it)!!.run {
                fun intOnPos(pos: Int) = this.groups[pos]!!.value.toInt()

                AssigmentPair(
                    intOnPos(1)..intOnPos(2),
                    intOnPos(3)..intOnPos(4)
                )

            }
        }
    }

    fun noOfCompleteOverlap(): Int = assigmentPairs.count { it.hasCompleteOverlap() }
    fun noOfPartialOverlap():Int = assigmentPairs.count { it.hasPartialOverlap() }
}

fun main() {
    val d4Tasks = D4Tasks(FileHandler.getLinesFromFile("day4.txt"))
    //Task A, answer: 538
    d4Tasks.noOfCompleteOverlap().printIt()
    //Task B, answer: 792
    d4Tasks.noOfPartialOverlap().printIt()

}