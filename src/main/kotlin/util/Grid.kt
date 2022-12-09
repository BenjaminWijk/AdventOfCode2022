package util

object Grid {
    fun processGrid(ySize: Int, xSize: Int, block: (y: Int, x: Int) -> Unit) {

        for (y in 0 until ySize) {
            for (x in 0 until xSize) {
                block(y, x)
            }
        }
    }
}