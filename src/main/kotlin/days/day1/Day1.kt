package days.day1

import days.AbstractDay
import extension.allInts
import extension.inputGroups

class Day1: AbstractDay() {
    override fun part1(): Int = solve(1)

    override fun part2(): Int = solve(3)

    private fun solve(size: Int = 1): Int = input.inputGroups().map{ it.allInts().sum() }.sortedDescending().take(size).sum()
}

fun main(){
    val day1 = Day1()
    println(day1.part1())
    println(day1.part2())
}
