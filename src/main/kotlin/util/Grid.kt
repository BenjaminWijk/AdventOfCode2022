package util

object Grid {
    fun processGrid(yMax: Int, xMax: Int, yMin: Int = 0, xMin: Int = 0, block: (y: Int, x: Int) -> Unit) {

        for (y in yMin until yMax) {
            for (x in xMin until xMax) {
                block(y, x)
            }
        }
    }

    fun printGrid(yMax: Int, xMax: Int, yMin: Int = 0, xMin: Int = 0, block: (y: Int, x: Int) -> String) {
        for (y in yMin until yMax) {
            for (x in xMin until xMax) {
                if(y > 0 && x == 0) println()
                print(block(y,x))
            }
        }
    }

    inline fun <reified T> createGrid(yMax: Int, xMax: Int, initValue: (y: Int, x: Int) -> T): Array<Array<T>> =
        Array(yMax) { y -> Array(xMax) { x -> initValue(y, x) } }


}