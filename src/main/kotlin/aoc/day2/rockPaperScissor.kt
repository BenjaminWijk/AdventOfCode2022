package aoc.day2

enum class RpsMove(val initChar:Char, val responseChar: Char, val bonusPoint: Int){
    ROCK('A', 'X', 1),
    PAPER('B', 'Y', 2),
    SCISSOR('C', 'Z', 3);

    fun losesAgainst() = when(this){
        ROCK -> PAPER
        PAPER -> SCISSOR
        SCISSOR -> ROCK
    }
    fun winsAgainst() = when(this){
        ROCK -> SCISSOR
        PAPER -> ROCK
        SCISSOR -> PAPER
    }
}


enum class IntendedMatchOutcome(val char: Char){
    LOSE('X'),
    DRAW('Y'),
    WIN('Z');
}