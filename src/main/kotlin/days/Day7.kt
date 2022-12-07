package days


fun <T> MutableList<T>.reset(default: T) {
    this.clear()
    this.add(default)
}

class Day7 : Day() {
    override fun part1(): Any {
        val disk = disk()
        return disk.filter { it.value <= 100000 }.values.sum()
    }

    override fun part2(): Any {
        val diskSpaceTotal = 70000000
        val minSize = 30000000
        val disk = disk()
        val freeSpace =  diskSpaceTotal-disk.maxOf {it.value }
        return disk.filter { d -> d.value >= minSize-freeSpace}.minBy { it.value }.value
    }
    
    fun disk(): MutableMap<String, Int> {
        val sizes = mutableMapOf<String, Int>()
        val path = mutableListOf<String>()
        input.split("\n").filterNot { it.startsWith("dir") }.map { "$it @".split(" ") }
            .forEach { (dolOrInt, cmOrName, cdPath) ->
                when (dolOrInt) {
                    "$" -> when (cmOrName) {
                        "cd" -> when (cdPath) {
                            "/" -> path.reset("root")
                            ".." -> path.removeLast()
                            else -> path.add(cdPath)
                        }
                    }
                    else -> {
                        path.scan(""){ acc, p -> "$acc/$p"}.forEach { 
                            sizes.putIfAbsent(it,0)
                            sizes[it] = sizes[it]!! + dolOrInt.toInt()
                        }
                    }
                }
            }
        return sizes
    }
}

fun main(){
    val d = Day7()
    println(d.part1())
    println(d.part2())
}