package aoc.day4

class AssigmentPair(val rangeOne: IntRange, val rangeTwo: IntRange) {

    fun hasCompleteOverlap(): Boolean =
        (rangeOne.first <= rangeTwo.first && rangeOne.last >= rangeTwo.last)
                || (rangeTwo.first <= rangeOne.first && rangeTwo.last >= rangeOne.last)

    //There's probably a muuuuuuuch faster way to do this but can't be arsed.
    fun hasPartialOverlap(): Boolean {
        rangeOne.forEach {
            if(rangeTwo.contains(it)) return true
        }
        return false
    }

}