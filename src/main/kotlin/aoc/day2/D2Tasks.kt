package aoc.day2

import aoc.day2.IntendedMatchOutcome.*

import util.FileHandler

class D2Task(val moves: List<String>) {

    fun task1Score(): Int {
        var totalScore = 0

        for (move in moves) {
            val initiatorMove = RpsMove.matching { move[0] == initChar }
            val responseMove = RpsMove.matching { move[2] == responseChar }

            totalScore += calculateRoundPoints(initiatorMove, responseMove)
        }

        return totalScore
    }

    fun task2Score(): Int {
        var totalScore = 0

        for (move in moves) {
            val initiatorMove = RpsMove.matching { move[0] == initChar }

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
    d2Task.task1Score().also { println(it) }
    d2Task.task2Score().also { println(it) }
}