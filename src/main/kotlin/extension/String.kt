package extension

fun String.allInts(delimiter: String = "") = this.split(delimiter).mapNotNull { s-> s.toIntOrNull() }
fun String.groups() = this.split("\n\n")