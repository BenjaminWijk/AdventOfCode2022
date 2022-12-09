package util

object Grid {
    fun processGrid(yMax: Int, xMax: Int, yMin: Int = 0, xMin: Int = 0, block: (y: Int, x: Int) -> Unit) {

        for (y in yMin until yMax) {
            for (x in xMin until xMax) {
                block(y, x)
            }
        }
    }
}