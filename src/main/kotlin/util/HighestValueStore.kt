package main.kotlin.util

class HighestValueStore(val size: Int) {

    val values = LongArray(size) { 0 }

    fun getLowestIndex(): Int {
        var lowestIndex = 0

        //we start off at 0 index, no need to compare with self
        for (i in 1 until size) {
            if (values[lowestIndex] > values[i])
                lowestIndex = i
        }
        return lowestIndex
    }

    fun put(newValue: Long) = getLowestIndex().let { lowest ->
        if (newValue > values[lowest])
            values[lowest] = newValue
    }

}