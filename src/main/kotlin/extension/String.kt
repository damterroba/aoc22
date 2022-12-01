package extension

fun String.allInts() = this.split("\n").mapNotNull { s-> s.toIntOrNull() }
fun String.groups() = this.split("\n\n")