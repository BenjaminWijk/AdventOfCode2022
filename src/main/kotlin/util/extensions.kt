package util

import kotlin.reflect.KClass

fun <T> T.printIt(prefix: String = "", suffix: String = ""): T = also {
    println(prefix + this + suffix)
}

//clazz not directly used in function, but required to "attach" extension function
@Suppress("UnusedReceiverParameter")
inline fun <reified T: Enum<T>, clazz: KClass<T>> clazz.matching(predicate: (T) -> Boolean) = enumValues<T>().first(predicate)