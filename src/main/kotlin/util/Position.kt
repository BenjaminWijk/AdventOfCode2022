package util

//This one ain't efficient at all most likely, but I'm ok with it. :))))))
class Position private constructor(val y: Int, val x: Int) {

    companion object {
        private val map = mutableMapOf<String, Position>()

        fun of(y: Int, x: Int): Position {
            return map[posStr(y, x)]
                ?: Position(y, x).also { map[it.toString()] = it }
        }

        fun posStr(y: Int, x: Int) = "$y,$x"
    }

    override fun toString(): String = posStr(y, x)

}