package aoc.day1

import util.printIt
import util.FileHandler

class D1Task {

    fun findTopElves(noOfTopElves: Int): String {
        val calorieHighScore = CalorieHighScore(noOfTopElves)

        var currentElfTotal = 0
        fun calcAndReset() {
            calorieHighScore.put(currentElfTotal)
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

        return calorieHighScore.values.sum().toString()
    }

}

class CalorieHighScore(val size: Int) {

    val values = IntArray(size) { 0 }

    fun getLowestIndex(): Int {
        var lowestIndex = 0

        //we start off at 0 index, no need to compare with self
        for (i in 1 until size) {
            if (values[lowestIndex] > values[i])
                lowestIndex = i
        }
        return lowestIndex
    }

    fun put(newValue: Int) = getLowestIndex().let { lowest ->
        if (newValue > values[lowest])
            values[lowest] = newValue
    }

}


fun main() {
    //Task A, answer: 67633
    D1Task().findTopElves(1).printIt()
    //Task B, answer: 199628
    D1Task().findTopElves(3).printIt()
}