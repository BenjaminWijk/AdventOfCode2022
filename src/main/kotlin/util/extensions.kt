package main.kotlin.util

fun <T> T.printResult(): T = also { println(this) }