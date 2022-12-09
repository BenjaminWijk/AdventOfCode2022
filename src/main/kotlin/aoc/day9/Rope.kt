package aoc.day9

import aoc.day9.Direction.*
import util.Position
import kotlin.math.abs

enum class Direction(val char: Char) {
    UP('U'),
    DOWN('D'),
    LEFT('L'),
    RIGHT('R');

    companion object {
        private val charToEnum = values().associateBy { it.char }
        fun of(char: Char) = charToEnum[char]!!

    }
}

class Rope(val length: Int, val visualiser: RopeVisualiser? = null) {
    val ropePositions = Array(length) { Position.of(0, 0) }

    var headPos
        get() = ropePositions[0]
        set(value) {
            ropePositions[0] = value
        }

    val tailTrail = mutableSetOf(headPos)

    fun moveHead(direction: Direction, times: Int) {
        visualiser?.visualiseCurrentRopeState(ropePositions, direction, times)

        repeat(times) {
            val (oldY, oldX) = headPos.y to headPos.x
            val (newY, newX) = when (direction) {
                UP -> oldY - 1 to oldX
                DOWN -> oldY + 1 to oldX
                LEFT -> oldY to oldX - 1
                RIGHT -> oldY to oldX + 1
            }

            headPos = Position.of(newY, newX)

            for (i in 1 until length) {
                val updateCancelled = updateLink(i)
                //Link still touching previous value? Rest is unchanged and we can perform next move
                if (updateCancelled) break
            }
        }
    }

    /**
     * @return - if update was cancelled (true == cancelled)
     */
    private fun updateLink(idx: Int): Boolean {
        val link = ropePositions[idx]
        val linkAhead = ropePositions[idx - 1]

        fun stillTouching(): Boolean {
            return abs(linkAhead.x - link.x) <= 1 && abs(linkAhead.y - link.y) <= 1
        }

        //Still in contact, no need to do anything else
        if (stillTouching()) return true

        var newX = link.x
        var newY = link.y

        //Adjust row if needed
        if (link.y != linkAhead.y) {
            if (link.y > linkAhead.y) newY-- else newY++
        }
        //Adjust column if needed
        if (link.x != linkAhead.x) {
            if (link.x > linkAhead.x) newX-- else newX++
        }

        ropePositions[idx] = Position.of(newY, newX)

        //Keep track of tail
        if (idx == length - 1) {
            tailTrail.add(ropePositions[idx])
        }

        return false
    }

}