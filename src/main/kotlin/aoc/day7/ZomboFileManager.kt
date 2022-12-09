package aoc.day7

class ZomboFileManager {

    val rootFolder = ZomboFolder("/", null)
    var currFolder = rootFolder

    fun runInput(input: List<String>) {
        input.forEach {
            when (ZomboCommand.commandTypeOf(it)) {
                ZomboCommand.LIST -> printCurrFolder()
                ZomboCommand.CHANGE_DIRECTORY -> changeDirectory(it)
                ZomboCommand.CREATE_DIRECTORY -> createDirectory(it)
                ZomboCommand.CREATE_FILE -> createFile(it)
            }
        }
    }

    private fun createFile(command: String) {
        val (size, name) = command.split(" ")
        currFolder.files.add(ZomboFile(size.toInt(), name))
    }

    private fun createDirectory(command: String) {
        val name = command.split(" ")[1]
        currFolder.subFolders.add(ZomboFolder(name, currFolder))
    }

    private fun printCurrFolder() {
        currFolder.files.forEach { println(it.name) }
        currFolder.subFolders.forEach { println("${it.name}/") }
    }

    private fun changeDirectory(cdCommand: String) {
        val arg = cdCommand.split(" ")[2]

        currFolder = when (ZomboCommand.CdArgument.of(arg)) {
            ZomboCommand.CdArgument.ROOT -> rootFolder
            ZomboCommand.CdArgument.STEP_OUT -> currFolder.parentFolder ?: currFolder
            ZomboCommand.CdArgument.STEP_IN -> currFolder.findSubFolder(arg)
        }
    }

    fun getSizeOfAllFolders(): List<Int> {
        val sizes = mutableListOf<Int>()
        rootFolder.performOnSubFoldersAndSelf {
            sizes.add(it.calculateSize())
        }

        return sizes
    }
}