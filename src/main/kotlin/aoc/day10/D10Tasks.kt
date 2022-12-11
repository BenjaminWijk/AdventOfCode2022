package aoc.day10

import util.FileHandler
import util.Grid
import util.printIt

class D10Tasks(val inputs: List<String>) {

    val cyclesToTrack = setOf(20, 60, 100, 140, 180, 220)

    var sixSignalSum = 0
    var currCycle = 0
    var register = 1

    fun calculateSignalStrengths(renderSprite: Boolean): Int {
        val crtMonitor = if(renderSprite) CrtMonitor(6,40) else null

        inputs.forEach { input ->
            input.split(" ").let { command ->
                fun runNextCycle() {
                    crtMonitor?.updatePixelFromCycle(currCycle, register)
                    currCycle++

                    if (cyclesToTrack.contains(currCycle)) {
                        sixSignalSum += register * currCycle
                    }
                }

                val operation = Operation.valueOf(command[0])
                repeat(operation.cycles) { runNextCycle() }

                when (operation) {
                    Operation.addx -> register += command[1].toInt()
                    Operation.noop -> {
                    }
                }
            }
        }

        crtMonitor?.printMonitor()

        return sixSignalSum
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

fun main() {
    val input = FileHandler.getLinesFromFile("day10.txt")

    //TaskA, answer: 12560
    D10Tasks(input).calculateSignalStrengths(renderSprite = false).printIt()
    //TaskB, answer: PLPAFBCL
    D10Tasks(input).calculateSignalStrengths(renderSprite = true)

    //val d10Sample = D10Tasks(FileHandler.getLinesFromFile("day10Sample.txt"))
    //d10Sample.calculateSignalStrengths(true)


}