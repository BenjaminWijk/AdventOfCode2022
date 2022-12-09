package main.kotlin.util

fun <T> T.printIt(prefix: String = "", suffix: String = ""): T = also {
    println(prefix + this + suffix)
}