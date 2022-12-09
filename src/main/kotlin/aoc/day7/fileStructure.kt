package aoc.day7


data class ZomboFile(val size: Int, val name: String)

data class ZomboFolder(
    val name: String,
    val parentFolder: ZomboFolder?,
    val subFolders: MutableList<ZomboFolder> = mutableListOf(),
    val files: MutableList<ZomboFile> = mutableListOf()
) {

    var lastCalculatedSize: Int? = null

    fun calculateSize(): Int = lastCalculatedSize ?: (subFolders.sumOf { it.calculateSize() } + files.sumOf { it.size }).also { lastCalculatedSize = it }

    fun findSubFolder(name: String) =
        subFolders.find { it.name == name }
            ?: throw Exception("folder with name $name not found in ${this.name}")

    fun performOnSubFoldersAndSelf(block: (ZomboFolder) -> Unit) {
        subFolders.forEach {
            it.performOnSubFoldersAndSelf(block)
        }
        block(this)
    }
}

