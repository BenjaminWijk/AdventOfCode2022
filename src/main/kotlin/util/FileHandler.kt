package util

import java.io.File

object FileHandler {
    fun getLinesFromFile(filename:String): List<String> = File(filename).useLines { it.toList() }
}