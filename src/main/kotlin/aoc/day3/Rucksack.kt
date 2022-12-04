package aoc.day3

class Rucksack(val completeContent: String) {
    private val midPoint = completeContent.length / 2

    private val firstHalf = completeContent.substring(0 until midPoint)
    private val secondHalf = completeContent.substring(midPoint until completeContent.length)

    fun findSharedItem(): Char{
        val firstSet = mutableSetOf<Char>()
        firstSet.addAll(firstHalf.map { it })

        for(c in secondHalf){
            if(firstSet.contains(c)) return c
        }

        throw Exception("No shared item found")
    }

}