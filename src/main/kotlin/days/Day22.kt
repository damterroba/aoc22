package days

import extension.groups
import java.awt.Point


operator fun Point.plus(o: Point): Point {
    return Point(x + o.x, y + o.y)

}

class Day22 : Day() {
    val lines: List<String>
    val ops: List<String>

    init {
        val (grid, opLine) = input.groups()
        lines = grid.split("\n")
        ops = opLine.replace("L", " L ").replace("R", " R ").split(" ")
    }

    fun nextPos(p: Point, facing: String): Point = p + when (facing) {
        "up" -> Point(0, -1)
        "down" -> Point(0, 1)
        "left" -> Point(-1, 0)
        "right" -> Point(1, 0)
        else -> Point(0, 0)
    }

    fun move(p: Point, amount: Int, facing: String): Point {
        var pos = p
        val fx = lines[pos.y].indexOfFirst { it in listOf('.', '#') }
        val lx = lines[pos.y].indexOfLast { it in listOf('.', '#') }
        val fy = lines.indexOfFirst { it.getOrElse(pos.x) { '=' } in listOf('.', '#') }
        val ly = lines.indexOfLast { it.getOrElse(pos.x) { '=' } in listOf('.', '#') }
        repeat(amount) {
            val next = nextPos(pos, facing)
            pos = if (facing == "right" && pos.x == lx) { // facing right end of line
                if (lines[pos.y][fx] == '.') {
                    Point(fx, pos.y)
                } else {
                    pos
                }
            } else if (facing == "left" && pos.x == fx) { // facing left beginnning of line
                if (lines[pos.y][lx] == '.') {
                    Point(lx, pos.y)
                } else {
                    pos
                }
            } else if (facing == "down" && pos.y == ly) { // facing right end of line
                if (lines[fy][pos.x] == '.') {
                    Point(pos.x, fy)
                } else {
                    pos
                }
            } else if (facing == "up" && pos.y == fy) { // facing left beginnning of line
                if (lines[ly][pos.x] == '.') {
                    Point(pos.x, ly)
                } else {
                    pos
                }
            } else {
                if (lines[next.y][next.x] == '.') { // wall
                    next
                } else {
                    pos
                }
            }
        }


        return pos
    }

    override fun part1(): Any {

        val directions = listOf<String>("right", "down", "left", "up")
        var facing = "right"
        val fLine = lines.indexOfFirst { it.contains('.') }
        var pos = Point(lines[fLine].indexOfFirst { it == '.' }, fLine)

        ops.forEach {
            if (it.toIntOrNull() != null) {
                pos = move(pos, it.toInt(), facing)
            } else {
                facing = when (it) {
                    "L" -> directions[(directions.size + directions.indexOf(facing) - 1) % directions.size]
                    else -> directions[(directions.size + directions.indexOf(facing) + 1) % directions.size]
                }
            }
        }
        val row = pos.y + 1
        val col = pos.x + 1
        val facingInt = directions.indexOf(facing)
        return (1000 * row) + (4 * col) + facingInt
    }

    fun getPart(p: Point): Int {
        return when {
            p.x in (50..99) && p.y in (0..49) -> 1
            p.x in (100..149) && p.y in (0..49) -> 2
            p.x in (50..99) && p.y in (50..99) -> 3
            p.x in (0..49) && p.y in (100..149) -> 4
            p.x in (50..99) && p.y in (100..149) -> 5
            else -> 6
        }
    }

    fun destination(p: Point, facing: String): Pair<Point, String> {
        val transitions = mapOf(
            Pair(1, "up") to Pair(Point(0, p.x + 100), "right"),
            Pair(1, "left") to Pair(Point(0, 149 - p.y), "right"),
            Pair(2, "up") to Pair(Point(p.x - 100, 199), "up"),
            Pair(2, "right") to Pair(Point(99, 149 - p.y), "left"),
            Pair(2, "down") to Pair(Point(99, p.x - 50), "left"),
            Pair(3, "right") to Pair(Point(p.y + 50, 49), "up"),
            Pair(3, "left") to Pair(Point(p.y - 50, 100), "down"),
            Pair(4, "left") to Pair(Point(50, 149 - p.y), "right"),
            Pair(4, "up") to Pair(Point(50, p.x + 50), "right"),
            Pair(5, "right") to Pair(Point(149, 149 - p.y), "left"),
            Pair(5, "down") to Pair(Point(49, p.x + 100), "left"),
            Pair(6, "left") to Pair(Point(p.y - 100, 0), "down"),
            Pair(6, "down") to Pair(Point(p.x + 100, 0), "down"),
            Pair(6, "right") to Pair(Point(p.y - 100, 149), "up"),
        )
        return transitions.getOrDefault(Pair(getPart(p), facing), Pair(nextPos(p, facing), facing))
    }

    override fun part2(): Any {
        val directions = listOf<String>("right", "down", "left", "up")
        var facing = "right"
        val fLine = lines.indexOfFirst { it.contains('.') }
        var pos = Point(lines[fLine].indexOfFirst { it == '.' }, fLine)
        var move: Pair<Point, String>
        ops.forEach {
            if (it.toIntOrNull() != null) {
                repeat(it.toInt()) r@{
                    if ((pos.x % 50 == 0 && facing == "left") || (pos.x % 50 == 49 && facing == "right") || (pos.y % 50 == 0 && facing == "up") || (pos.y % 50 == 49) && facing == "down") {
                        move = destination(pos, facing)
                    } else {
                        move = Pair(nextPos(pos, facing), facing)
                    }
                    if (lines[move.first.y][move.first.x] == '.') {
                        pos = move.first
                        facing = move.second
                    } else {
                        return@forEach
                    }
                }
            } else {
                facing = when (it) {
                    "L" -> directions[(directions.size + directions.indexOf(facing) - 1) % directions.size]
                    else -> directions[(directions.size + directions.indexOf(facing) + 1) % directions.size]
                }
            }
        }
        val row = pos.y + 1
        val col = pos.x + 1
        val facingInt = directions.indexOf(facing)
        return (1000 * row) + (4 * col) + facingInt
    }
}

fun main() {
    val d = Day22()
    println(d.part1())
    println(d.part2())
}