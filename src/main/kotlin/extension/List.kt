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

fun <T> Iterable<T>.until(cond: (T)->Boolean): List<T>{
    var broke = false
    val res= mutableListOf<T>()
    forEach { 
        if(!broke){
            res.add(it)
        }
        if(!cond(it)){
            broke =true
        }
        
    }
    return res
}

fun List<Int>.produce():Int{
    return this.reduce{ acc, t-> acc*t }
}

