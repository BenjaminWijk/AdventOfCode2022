package main.kotlin.util

fun Regex.group(input: String, group: Int): String? = find(input)?.groups?.get(group)?.value

fun Regex.groups(input: String, vararg groups: Int): List<String>?{
    val foundGroups = find(input)?.groups ?: return null

    return groups.map {
        foundGroups[it]?.value ?: throw Exception("group $it not found in regex result")
    }
}
