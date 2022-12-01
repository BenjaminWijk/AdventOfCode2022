package util

import java.io.File

object FileHandler {
    val defaultPath = "inputFiles/"
    fun getLinesFromFile(filename:String): List<String> = File(defaultPath + filename).useLines { it.toList() }
}