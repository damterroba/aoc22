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
        repeat(10) {
            val current = states.last()
            val moves = mutableMapOf<Point, Point>()
            val nextState = mutableListOf<Point>()
            current.forEach { p ->
                val adjacents = p.adjacents().filter { it in states.last() }.size
                if (adjacents == 0) {
                    nextState.add(p)
                } else {
                    val nextMove = (it..it + directions.size).firstOrNull { index ->
                        search[directions[index % directions.size]]!!.map { d -> p + d }
                            .none { p -> p in states.last() }
                    }
                    if (nextMove != null) {
                        moves.put(p, p.move(directions[nextMove%directions.size]))
                    } else { // can't move
                        nextState.add(p)
                    }
                }
            }
            moves.forEach{m ->
                if(m.value !in moves.filter { it != m }.values){
                    nextState.add(m.value)
                }else{
                    nextState.add(m.key)
                }
            }
            states.add(nextState)
        }
        val minY = states.last().minOf { it.y }
        val maxY = states.last().maxOf { it.y }
        val minX = states.last().minOf { it.x }
        val maxX = states.last().maxOf { it.x }
        var c = 0
        (minY..maxY).forEach {y->
            (minX..maxX).forEach { x->
                if(Point(x,y)!in states.last() ){
                    c++
                }
            }
        }

        return c
    }


    override fun part2(): Any {
        states.clear()
        states.add(elves)
        var i = 0
        while(true) {
            val current = states.last()
            val moves = mutableMapOf<Point, Point>()
            val nextState = mutableListOf<Point>()
            current.forEach { p ->
                val adjacents = p.adjacents().filter { it in states.last() }.size
                if (adjacents == 0) {
                    nextState.add(p)
                } else {
                    val nextMove = (i..i+ directions.size).firstOrNull { index ->
                        search[directions[index % directions.size]]!!.map { d -> p + d }
                            .none { p -> p in states.last() }
                    }
                    if (nextMove != null) {
                        moves.put(p, p.move(directions[nextMove%directions.size]))
                    } else { // can't move
                        nextState.add(p)
                    }
                }
            }
            if(moves.size == 0) {
                return ++i
            }else {
                moves.forEach { m ->
                    if (m.value !in moves.filter { it != m }.values) {
                        nextState.add(m.value)
                    } else {
                        nextState.add(m.key)
                    }
                }
                states.add(nextState)
            }
            i++
        }
    }
}

fun main() {
    val d = Day23()
    println(d.part1())
    println(d.part2())
}