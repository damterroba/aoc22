package days

import extension.until
import kotlin.system.measureTimeMillis

class Day8 : Day() {
    var forest: List<List<Int>>

    init {
        forest = input.split("\n").map { it.map { d -> d.digitToInt() } }
    }

    override fun part1(): Int {
        return trees().count {
            it.second.map { m -> m.value.fold(true) { a, e -> a && e } }.any { t -> t }
        }
    }

    override fun part2(): Int {
        return trees().maxOf {
            listOf('L', 'U', 'R', 'D').map t@{ key ->
                return@t if (it.second[key]!!.size == 0) {
                    0
                } else {
                    it.second[key]!!.until { it }.size
                }
            }.reduce{a,e -> a*e}
        }
    }

    fun trees(): List<Pair<Pair<Int, Int>, Map<Char, List<Boolean>>>> {
        return (forest.indices).flatMap { y ->
            (0 until forest[0].size).map { x ->
                val l2 = (x - 1 downTo 0).map {
                    forest[y][x] > forest.get(y).getOrElse(it) { -1 }
                }
                val r2 = (x + 1 until forest[y].size).map {
                    forest[y][x] > (forest.getOrNull(y)?.getOrElse(it) { -1 } ?: -1)
                }
                val u2 = (y - 1 downTo 0).map { forest[y][x] > (forest.getOrNull(it)?.getOrElse(x) { -1 } ?: -1) }
                val d2 = (y + 1 until forest.size).map {
                    forest[y][x] > (forest.getOrNull(it)?.getOrElse(x) { -1 } ?: -1)
                }
                Pair(y, x) to mapOf('L' to l2, 'U' to u2, 'R' to r2, 'D' to d2)
            }
        }
    }
}

fun main() {
    val d = Day8()
    val t = measureTimeMillis {
        println(d.part1())
        println(d.part2())
    }
    println("it took me $t ms to run")
}