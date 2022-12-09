package aoc.day2

import aoc.day2.IntendedMatchOutcome.*
import util.printIt

import util.FileHandler

class D2Task(val moves: List<String>) {

    fun task1Score(): Int {
        var totalScore = 0

        for (move in moves) {
            val initiatorMove = RpsMove.values().first { move[0] == it.initChar }
            val responseMove = RpsMove.values().first { move[2] == it.responseChar }

            totalScore += calculateRoundPoints(initiatorMove, responseMove)
        }

        return totalScore
    }

    fun task2Score(): Int {
        var totalScore = 0

        for (move in moves) {
            val initiatorMove = RpsMove.values().first { move[0] == it.initChar }

            val intendedMatchOutcome = IntendedMatchOutcome.values().first { it.char == move[2] }
            val responseMove = when (intendedMatchOutcome) {
                LOSE -> initiatorMove.winsAgainst()
                DRAW -> initiatorMove
                WIN -> initiatorMove.losesAgainst()
            }

            totalScore += calculateRoundPoints(initiatorMove, responseMove)
        }

        return totalScore
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