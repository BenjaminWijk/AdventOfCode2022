package aoc.day4

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

    fun calculateAssignmentsWithCompleteOverlap(): Int = assigmentPairs.count { it.hasCompleteOverlap() }
    fun calculateAssignmentsWithPartialOverlap():Int = assigmentPairs.count { it.hasPartialOverlap() }
}

fun main() {
    val d4Tasks = D4Tasks(FileHandler.getLinesFromFile("day4.txt"))
    //Task A
    d4Tasks.calculateAssignmentsWithCompleteOverlap().also { println(it) }
    //Task B
    d4Tasks.calculateAssignmentsWithPartialOverlap().also { println(it) }

}