package aoc.day9

import aoc.day9.Direction.*
import util.Position
import kotlin.math.abs

enum class Direction(val char: Char) {
    UP('U'),
    DOWN('D'),
    LEFT('L'),
    RIGHT('R');

    companion object{
        private val charToEnum = values().associateBy { it.char }
        fun of(char: Char) = charToEnum[char]!!

    }
}

class Rope(val length: Int) {
    val ropePositions = Array<Position>(length){ Position.of(0,0)}

    var headPos
        get() = ropePositions[0]
        set(value) { ropePositions[0] = value}

    private val _tailTrail = mutableSetOf(headPos)
    val tailTrail: Set<Position>
        get() = _tailTrail

    fun moveHead(direction: Direction, times: Int) {
        repeat(times) {
            val oldPositions = ropePositions.copyOf()
            val (oldY, oldX) = headPos.y to headPos.x
            val (newY, newX) = when (direction) {
                UP -> oldY - 1 to oldX
                DOWN -> oldY + 1 to oldX
                LEFT -> oldY to oldX - 1
                RIGHT -> oldY to oldX + 1
            }

            headPos = Position.of(newY, newX)

            for(i in 1 until length) {
                updateNextLink(i, oldPositions)
            }
        }
    }

    private fun updateNextLink(idx: Int, previousPositions: Array<Position>) {
        val nextLink = ropePositions[idx]
        val prevLink = ropePositions[idx-1]

        fun stillTouchingHead(): Boolean {
            return abs(prevLink.x - nextLink.x) <= 1 && abs(prevLink.y - nextLink.y) <= 1
        }

        if (stillTouchingHead()) return

        ropePositions[idx] = previousPositions[idx-1]

        //keep track of tail
        if(idx == length -1){
            _tailTrail.add(ropePositions[idx])
        }
    }

}