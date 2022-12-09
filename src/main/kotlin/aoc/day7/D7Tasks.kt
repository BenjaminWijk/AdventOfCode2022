package aoc.day7

import util.FileHandler
import kotlin.math.max

class D7Tasks(val input: List<String>) {

    val fileManager = ZomboFileManager().also {
        it.runInput(input)
    }

    fun calcSumSizeOfSmallFolders(): Int{
        val maxSize = 100000

        return fileManager.getSizeOfAllFolders().filter { it < maxSize }.sum()
    }

    fun findSmallestRequiredFolderToDelete(): Int{
        val maxAllowedOccupiedSpace = 70000000 - 30000000

        val sizes = fileManager.getSizeOfAllFolders()

        val totalSize = fileManager.rootFolder.lastCalculatedSize!!
        var lowestFound = totalSize

        for(size in sizes){
            if(size > lowestFound) continue
            if(totalSize-size < maxAllowedOccupiedSpace)
                lowestFound = size
        }

        return lowestFound
    }




}

fun main() {

    val d7Tasks = D7Tasks(FileHandler.getLinesFromFile("day7.txt"))
    //Task A, answer: 1644735
    d7Tasks.calcSumSizeOfSmallFolders().also { println(it) }

    //Task B, answer: 1300850
    d7Tasks.findSmallestRequiredFolderToDelete().also { println(it) }

}