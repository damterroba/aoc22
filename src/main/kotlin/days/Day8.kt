package days

import extension.until

class Day8 : Day() {
    var forest: List<List<Int>>
    init{
        forest = input.split("\n").map { it.map { d -> d.digitToInt() } }
    }
    override fun part1(): Any {
        return bestTrees().flatten().count { it }
    }

    override fun part2(): Any {
        return forest.mapIndexed { y, r ->
            r.mapIndexed { x, c ->
                var left = (x-1 downTo 0).toList().until { forest[y][it] < c }.size
                var right = (x+1 until  forest[y].size).toList().until { forest[y][it] < c }.size
                var up = (y-1 downTo 0).toList().until { forest[it][x] <c }.size
                var down= (y+1 until forest.size).toList().until{ forest[it][x] <c }.size
                up*left*down*right
            }
        }.flatten().max()
    }
    
    fun bestTrees(): List<List<Boolean>> {
        return (0..forest.size - 1).map { y ->
            (0 until forest[0].size).map { x ->
                val left = if (x > 0) forest[y][x] > (0 until x).map { forest.getOrNull(y)?.getOrElse(it) { -1 } ?: -1 }
                    .max() else true
                val up = if (y > 0) forest[y][x] > (0 until y).map { forest.getOrNull(it)?.getOrElse(x) { -1 } ?: -1 }
                    .max() else true
                val down = if (y < forest.size - 1) forest[y][x] > (y + 1 until forest.size).map {
                    forest.getOrNull(it)?.getOrElse(x) { -1 } ?: -1
                }.max() else true
                val right = if (x < forest[y].size - 1) forest[y][x] > (x + 1 until forest[y].size).map {
                    forest.getOrNull(y)?.getOrElse(it) { -1 } ?: -1
                }.max() else true
                left || up || right || down
            }
        }
    }
}

fun main() {
    val d = Day8()
    println(d.part1())
    println(d.part2())
}