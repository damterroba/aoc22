package days

class Day4: Day() {
    override fun part1(): Int = solve { it.first().containsAll(it.last()) || it.last().containsAll(it.first()) }

    override fun part2(): Int = solve {
        it.first().intersect(it.last()).size > 0
    }

    fun inputAsPairOfInt() = input.split("\n").map{
        it.split(",")
    }.map{
        it.map{ s-> val int = s.split("-"); (int.first().toInt()..int.last().toInt()).toSet()}
    }

    fun solve(countCond : (List<Set<Int>>) -> Boolean): Int {
        return inputAsPairOfInt().count(countCond)
    }
}

fun main(){
    val d = Day4()
    println(d.part1())
    println(d.part2())
}