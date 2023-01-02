package days

import java.awt.Point

fun Point.cross() = listOf(
    this + Point(-1, 0),
    this + Point(1, 0),
    this + Point(0, -1),
    this + Point(0, 1),
)

class Day24 : Day() {
    val size = input.split("\n").size
    val walls = input.split("\n")
        .flatMapIndexed { y: Int, s: String -> s.mapIndexedNotNull { x, c -> if (c == '#') Point(x, y) else null } }
    var winds = mutableListOf<List<Pair<Point, Char>>>()
    val maxY = walls.maxOf { it.y }
    val maxX = walls.maxOf { it.x }

    val start = Point(input.split("\n")[0].indexOfFirst { it == '.' }, 0)
    val end = Point(input.split("\n").last().indexOfFirst { it == '.' }, maxY)

    fun move(wind: Pair<Point, Char>): Pair<Point, Char> {
        return when (wind.second) {
            '>' -> {
                val cand = wind.first + Point(1, 0)
                if (cand.x < maxX) Pair(cand, wind.second) else Pair(Point(1, wind.first.y), wind.second)
            }// move right
            '<' -> {
                val cand = wind.first + Point(-1, 0)
                if (cand.x > 0) Pair(cand, wind.second) else Pair(Point(maxX - 1, wind.first.y), wind.second)
            } // left
            'v' -> {
                val cand = wind.first + Point(0, 1)
                if (cand.y < maxY) Pair(cand, wind.second) else Pair(Point(wind.first.x, 1), wind.second)
            } // down
            '^' -> {
                val cand = wind.first + Point(0, -1)
                if (cand.y > 0) Pair(cand, wind.second) else Pair(Point(wind.first.x, maxY - 1), wind.second)
            } // up
            else -> wind
        }
    }

    override fun part1(): Int {
        winds.clear()
        winds.add(input.split("\n")
            .flatMapIndexed { y: Int, s: String ->
                s.mapIndexedNotNull { x, c ->
                    if (c != '.') Point(
                        x,
                        y
                    ) to c else null
                }
            })
        return traverse(start, end)

    }

    override fun part2(): Int{
        return part1() + traverse(end, start) + traverse(start,end)
    }
    private fun traverse(from: Point, to : Point): Int {
        var current = setOf(from)
        var i = 0
        while (true) {
            ++i
            val nextwinds = winds.last().map { move(it) }
            winds.add(nextwinds)
            val targets = current.flatMap { target(it) }.toSet()
            if (to in targets) {
                return i
            }
            current = targets
        }
//        return i
    }

    fun target(p: Point): List<Point> {
        val t = p.cross()
            .filter { it.x in (0 .. maxX) && it.y in (0..maxY) && it !in winds.last().map { c -> c.first } }.toMutableList()
        if(p !in winds.last().map{it.first}){
            t.add(p)
        }
        
        return t
    }
}

fun main() {
    val d = Day24()
    println(d.part1())
    println(d.part2())
}