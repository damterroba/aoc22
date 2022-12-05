package days

import extension.allInts
import extension.groups
import extension.transpose

class Day5 : Day() {
    override fun part1(): String = solve(true)

    override fun part2(): String = solve()

    fun solve(rev: Boolean = false): String {
        val (stacks, moves) = input.groups()
        val t = stacks.split("\n").dropLast(1).map {
            it.chunked(4) { s -> s.replace("   ".toRegex(), "[#]").replace(" ", "") }
        }.transpose().map { it.filterNot { s -> s == "[#]" || s.isNullOrBlank() } }.toMutableList()
        moves.split("\n").forEach { it ->
            val (n, from, to) = it.allInts(" ")
            val targets = t[from - 1].take(n)
            if (rev) {
                t[to - 1] = targets.reversed() + t[to - 1]
            } else {
                t[to - 1] = targets + t[to - 1]
            }
            t[from - 1] = t[from - 1].drop(n)
        }
        return t.map { it.first()!!.filter { c -> c.isLetter() } }.joinToString("")
    }
}

fun main() {
    val d = Day5()
    println(d.part1())
    println(d.part2())
}