package extension

fun <T> List<List<T>>.transpose(default: T) :List<List<T>>{
    val cols= this.indices
    val maxRowSize = this.maxOf { it.size }
    return (0 until maxRowSize).map { cIndex ->
        cols.map {rIndex ->
            this.getOrNull(rIndex)?.getOrNull(cIndex) ?: default
        }
    }
}