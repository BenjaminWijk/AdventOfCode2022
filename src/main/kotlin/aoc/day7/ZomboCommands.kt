package aoc.day7

enum class ZomboCommand{
    LIST,
    CHANGE_DIRECTORY,
    CREATE_DIRECTORY,
    CREATE_FILE;

    companion object{
        private val createFileRegex = "^(\\d+) ([\\w.]+)".toRegex()

        fun commandTypeOf(str: String) = when{
            str.startsWith("$ ls") -> LIST
            str.startsWith("$ cd") -> CHANGE_DIRECTORY
            str.startsWith("dir") -> CREATE_DIRECTORY
            str.matches(createFileRegex) -> CREATE_FILE

            else -> throw Exception("unknown command: $str")
        }
    }

    enum class CdArgument{
        ROOT,
        STEP_OUT,
        STEP_IN;

        companion object{
            fun of(str: String) = when(str){
                "/" -> ROOT
                ".." -> STEP_OUT
                else -> STEP_IN
            }
        }
    }
}


