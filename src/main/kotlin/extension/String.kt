package extension

fun String.allInts() = this.split("\n").mapNotNull { s-> s.toIntOrNull() }
fun String.inputGroups() = this.split("\n\n")