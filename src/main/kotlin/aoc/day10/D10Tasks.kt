package aoc.day10

import util.FileHandler
import util.Grid
import util.printIt

class D10Tasks(val inputs: List<String>) {

    var currCycle = 0
    var register = 1

    fun processInput(runNextCycle: () -> Unit) {
        inputs.forEach { input ->
            input.split(" ").let { command ->
                val operation = Operation.valueOf(command[0])
                repeat(operation.cycles) { runNextCycle() }

                when (operation) {
                    Operation.addx -> register += command[1].toInt()
                    Operation.noop -> {
                    }
                }
            }
        }
    }

    fun showCrtImage(){
        val monitor = CrtMonitor(6, 40)
        processInput {
            monitor.updatePixelFromCycle(currCycle, register)
            currCycle++
        }

        monitor.printMonitor()
    }

    fun calculateImportantCycles():Int{
        val cyclesToTrack = setOf(20, 60, 100, 140, 180, 220)
        var signalSum = 0

        processInput {
            currCycle++
            if (cyclesToTrack.contains(currCycle)) {
                signalSum += register * currCycle
            }
        }
        return signalSum
    }

    class CrtMonitor(val height: Int, val width: Int) {
        val grid = Grid.createGrid(height, width) { '.' }

        fun updatePixelFromCycle(pixel: Int, register: Int) {
            val row = pixel / width
            val col = pixel % width

            if ((register - 1..register + 1).contains(col)){
                grid[row][col] = '#'
            }
        }

        fun printMonitor(){
            println()
            println()

            Grid.processGrid(height, width){ row, col ->
                if(col == 0) println()
                print(grid[row][col])
            }
        }
    }

}

//Not too happy about this one, code smells a lot.
fun main() {
    val input = FileHandler.getLinesFromFile("day10.txt")

    //TaskA, answer: 12560
    D10Tasks(input).calculateImportantCycles().printIt()
    //TaskB, answer: PLPAFBCL
    D10Tasks(input).showCrtImage()

    //val d10Sample = D10Tasks(FileHandler.getLinesFromFile("day10Sample.txt"))
    //d10Sample.calculateSignalStrengths(true)


}