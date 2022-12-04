package aoc.day4

class AssigmentPair(val r1: IntRange, val r2: IntRange) {

    fun hasCompleteOverlap(): Boolean =
        (r1.first <= r2.first && r1.last >= r2.last)
                || (r2.first <= r1.first && r2.last >= r1.last)

    //There's probably a muuuuuuuch faster way to do this but can't be arsed.
    fun hasPartialOverlap(): Boolean {
        r1.forEach {
            if(r2.contains(it)) return true
        }
        return false
    }

}