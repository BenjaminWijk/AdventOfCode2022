package util

object Grid {
    fun processGrid(yMax: Int, xMax: Int, yMin: Int = 0, xMin: Int = 0, block: (y: Int, x: Int) -> Unit) {

        for (y in yMin until yMax) {
            for (x in xMin until xMax) {
                block(y, x)
            }
        }
    }

    inline fun <reified T> createGrid(yMax: Int, xMax: Int, initValue: () -> T): Array<Array<T>> =
        Array(yMax) { Array(xMax) { initValue() } }


}