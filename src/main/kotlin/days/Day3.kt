package days

class Day3 : Day() {
    override fun part1(): Int {
        val groups = input.split("\n").map {
            it.chunked(it.length / 2) { s -> s.toSet() }
        }
        return groups.map { it[0].intersect(it[1]) }.sumOf { priority(it.first()) }
    }

    override fun part2(): Int {
        val sacks = input.split("\n").map { it.toSet() }.chunked(3)
        return sacks.map { it.reduce { a, e -> a.intersect(e) } }.sumOf { priority(it.first()) }
    }

    fun priority(c: Char): Int = when (c.isUpperCase()) {
        true -> 27 + (c.code - 'A'.code)
        false -> 1 + (c.code - 'a'.code)
    }

}

fun main() {
    val d = Day3()
    println(d.part1())
    println(d.part2())
}