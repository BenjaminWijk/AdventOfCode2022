package aoc.day9

import aoc.day9.Direction.*
import util.Grid
import util.Position

class RopeVisualiser(val input: List<Pair<Direction, Int>>) {

    var minX = 0
    var maxX = 0
    var minY = 0
    var maxY = 0
    var visualiseStep = 1

    init {
        determineGridSize()
    }

    fun visualiseCurrentRopeState(rope: Array<Position>, direction: Direction, times: Int) {
        val ropeDisplay = mutableMapOf<Position, Char>()
        for (i in rope.size - 1 downTo 0) {
            val char = if (i == 0) 'H' else i.toString()[0]
            ropeDisplay[rope[i]] = char
        }
        Grid.processGrid(maxY +1, maxX +1, minY, minX) { y, x ->
            if (x == minX) //new row
                println()

            print(ropeDisplay[Position.of(y, x)] ?: '.')
        }

        println()
        println()
        println("${visualiseStep++}.  $direction $times")
    }

    private fun determineGridSize() {
        var x = 0
        var y = 0
        minX = 0
        maxX = 0
        minY = 0
        maxY = 0

        input.forEach { (dir, times) ->
            repeat(times) {
                when (dir) {
                    UP -> {
                        y--
                        if (y < minY) minY = y
                    }
                    DOWN -> {
                        y++
                        if (y > maxY) maxY = y
                    }
                    LEFT -> {
                        x--
                        if (x < minX) minX = x
                    }
                    RIGHT -> {
                        x++
                        if (x > maxX) maxX = x
                    }
                }
            }
        }

    }


}