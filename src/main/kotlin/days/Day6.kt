package days

class Day6 : Day() {
    override fun part1(): Int = solve(4)

    override fun part2(): Int = solve(14)
    fun solve(n: Int) = input.asSequence().windowed(n) { it.toSet() }.indexOfFirst { it.size ==n } + n
}

fun main() {
    val d = Day6()
    println(d.part1())
    println(d.part2())
}