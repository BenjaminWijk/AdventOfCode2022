package aoc.day2

import aoc.day2.IntendedMatchOutcome.*
import util.printIt

import util.FileHandler
import util.matching

class D2Task(val moves: List<String>) {

    fun task1Score(): Int {
        return moves.sumOf { move ->
            val initiatorMove = RpsMove::class.matching { move[0] == it.initChar }
            val responseMove = RpsMove::class.matching { move[2] == it.responseChar }

            calculateRoundPoints(initiatorMove, responseMove)
        }
    }

    fun task2Score(): Int {
       return moves.sumOf { move ->
            val initiatorMove = RpsMove::class.matching { move[0] == it.initChar }

            val responseMove = when (IntendedMatchOutcome::class.matching { it.char == move[2] }) {
                LOSE -> initiatorMove.winsAgainst()
                DRAW -> initiatorMove
                WIN -> initiatorMove.losesAgainst()
            }

            calculateRoundPoints(initiatorMove, responseMove)
        }
    }

    private fun calculateRoundPoints(initiatorMove: RpsMove, responseMove: RpsMove): Int {
        val winBonus = 6
        val drawBonus = 3

        var score = responseMove.bonusPoint
        score += when (initiatorMove) {
            responseMove -> drawBonus
            responseMove.winsAgainst() -> winBonus
            else -> 0
        }
        return score
    }
}

fun main() {
    val moves = FileHandler.getLinesFromFile("day2.txt")
    val d2Task = D2Task(moves)

    //Answer: 13005
    d2Task.task1Score().printIt()
    //Answer: 11373
    d2Task.task2Score().printIt()
}