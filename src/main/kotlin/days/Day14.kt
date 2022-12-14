package days

import helpers.Point

class Day14 : Day() {
    override fun part1(): Any {
        return solve()

    }

    override fun part2(): Any {
        return solve(2) + 1
    }

    fun grid(cpt: Int): MutableMap<Point, Char> {
        val cave = mutableMapOf<Point, Char>().withDefault { '.' }
        val groups = input.split("\n").map { it.replace(" ", "").split("->").map { it.split(",") } }

        groups.forEach {
            it.windowed(2).forEach { s ->
                (minOf(s[0][0].toInt(), s[1][0].toInt())..maxOf(s[0][0].toInt(), s[1][0].toInt())).forEach { x ->
                    (minOf(s[0][1].toInt(), s[1][1].toInt())..maxOf(s[0][1].toInt(), s[1][1].toInt())).forEach { y ->
                        cave.putIfAbsent(Point(x, y), '#') // Rock
                    }
                }
            }
        }
        val minX = cave.minOf { it.key.first }
        val maX = cave.maxOf { it.key.first }
        val floor = cave.maxOf { it.key.second } + cpt
        (0 until floor).forEach { y ->
            (minX - 10..maX + 10).forEach { x ->
                cave.putIfAbsent(Point(x, y), '.')
            }
        }
        if (cpt > 0) {
            cave.put(Point(500, floor), '#')
        }
        return cave
    }

    fun solve(cpt: Int = 0): Int {
        val fallsFrom = Point(500, 0)
        val sands = mutableSetOf<Point>()
        val cave = grid(cpt)
        val floor = cave.filter { it.value == '#' }.maxOf { it.key.second }

        val canMoveAt = { p: Point -> p.second != floor + 1 && p !in sands && cave.getOrDefault(p, '.') == '.' }
        val dirs = { x: Int, y: Int -> listOf(Point(x, y + 1), Point(x - 1, y + 1), Point(x + 1, y + 1)) }
        var t = 0

        fun play(p: Point): Point? {
            var x1 = p.first
            var y1 = p.second

            while (dirs(x1, y1).any(canMoveAt)) {
                val nextPosition = dirs(x1, y1).first(canMoveAt)
                if (nextPosition.second == floor) {
                    return if (cpt > 0 && Point(x1, y1) != fallsFrom) {
                        Point(x1, y1)
                    } else {
                        null
                    }
                }
                x1 = nextPosition.first
                y1 = nextPosition.second
            }
            return if (Point(x1, y1) != fallsFrom) {
                Point(x1, y1)
            } else {
                null
            }
        }

        while (play(fallsFrom) != null) {
            val o = play(fallsFrom)
            if (o != null) {
                sands.add(o)
                cave.replace(o, 'o')
            }
            /**
             * Searching
             */
            t++
        }
        return if (cpt > 0) {
            (sands.size)
        } else {
            cave.filter { it.value == 'o' }.size
        }
    }
}

fun main() {
    val d = Day14()
    println(d.part1())
    println(d.part2())
}