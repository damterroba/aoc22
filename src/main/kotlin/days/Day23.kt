package days

import java.awt.Point

fun Point.adjacents() =
    listOf(
        Point(-1, -1),
        Point(0, -1),
        Point(1, -1),
        Point(-1, 1),
        Point(0, 1),
        Point(1, 1),
        Point(-1, 0),
        Point(1, 0),
    ).map { it + this }.toSet()

fun Point.move(dir: String) = when (dir) {
    "N" -> this + Point(0, -1)
    "S" -> this + Point(0, 1)
    "W" -> this + Point(-1, 0)
    "E" -> this + Point(1, 0)
    else -> this
}

class Day23 : Day() {
    val directions = listOf("N", "S", "W", "E")
    val search = mapOf(
        "N" to listOf(Point(-1, -1), Point(0, -1), Point(1, -1)),
        "S" to listOf(Point(-1, 1), Point(0, 1), Point(1, 1)),
        "W" to listOf(Point(-1, -1), Point(-1, 0), Point(-1, 1)),
        "E" to listOf(Point(1, -1), Point(1, 0), Point(1, 1)),
    )
    val elves = input.split("\n").flatMapIndexed { y: Int, s: String ->
        s.mapIndexedNotNull { index, c -> if (c == '#') Point(index, y) else null }
    }
    val states = mutableListOf(elves)
    override fun part1(): Any {
        var state = elves.toList()
        repeat(10) {
            state = turn(state, it)
        }
        val minY = state.minOf { it.y }
        val maxY = state.maxOf { it.y }
        val minX = state.minOf { it.x }
        val maxX = state.maxOf { it.x }
        var c = 0
        (minY..maxY).forEach { y ->
            (minX..maxX).forEach { x ->
                if (Point(x, y) !in states.last()) {
                    c++
                }
            }
        }

        return c
    }

    private fun turn(state: List<Point>, it: Int): List<Point> {
        val moves = mutableMapOf<Point, Point>()
        val nextState = mutableListOf<Point>()
        state.forEach { p ->
            val adjacents = p.adjacents().filter { it in state}.size
            if (adjacents == 0) {
                nextState.add(p)
            } else {
                val nextMove = (it..it + directions.size).firstOrNull { index ->
                    search[directions[index % directions.size]]!!.map { d -> p + d }
                        .none { p -> p in state }
                }
                if (nextMove != null) {
                    moves.put(p, p.move(directions[nextMove % directions.size]))
                } else { // can't move
                    nextState.add(p)
                }
            }
        }
        moves.forEach { m ->
            if (moves.values.count { it == m.value } ==1) {
                nextState.add(m.value)
            } else {
                nextState.add(m.key)
            }
        }
        return nextState
    }


    override fun part2(): Any {
        var state = elves
        var i = 0
        while (true) {
            val t = turn(state, i)
            if(state.toSet() == t.toSet()){
                return ++i
            }else{
                state = t
                i++
            }
        }
    }
}

fun main() {
    val d = Day23()
    println(d.part1())
    println(d.part2())
}