package aoc.day1

import main.kotlin.util.HighestValueStore
import util.printIt
import util.FileHandler

class D1Task {

    fun findTopElves(noOfTopElves: Int): String {
        val highestValueStore = HighestValueStore(noOfTopElves)

        var currentElfTotal = 0L
        fun calcAndReset() {
            highestValueStore.put(currentElfTotal)
            currentElfTotal = 0
        }

        for (line in FileHandler.getLinesFromFile("day1.txt")) {
            if (line.isBlank()) {
                calcAndReset()
                continue
            }
            currentElfTotal += line.toInt()
        }
        calcAndReset() //in case last line is not blank

        return highestValueStore.values.sum().toString()
    }

}




fun main() {
    //Task A, answer: 67633
    D1Task().findTopElves(1).printIt()
    //Task B, answer: 199628
    D1Task().findTopElves(3).printIt()
}