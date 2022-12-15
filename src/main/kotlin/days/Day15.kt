package days

import kotlin.math.absoluteValue

fun manhattan(p1: Pair<Long, Long>, p2: Pair<Long, Long>): Long {
    return (p1.first - p2.first).absoluteValue + (p1.second - p2.second).absoluteValue
}

class Day15 : Day() {
    override fun part1(): Any {
        return solve(2000000L)
    }

    override fun part2(): Any {
        return solve2(2000000L)
    }

    fun solve(line: Long): Int {
        val cor = parse()
        val beacons = cor.map { it.second }
        val minX = minOf(cor.minOf { it.first.first }, cor.minOf { it.second.first })
        val maxManh = cor.maxOf { manhattan(it.first, it.second) } + 1

        val maxX = maxOf(cor.maxOf { it.first.first }, cor.maxOf { it.second.first })


        val c = (minX - maxManh until maxX + maxManh).map {
            val p = Pair(it, line)
            if (p in beacons) {
                null
            } else {
                cor.none { b -> manhattan(p, b.first) <= manhattan(b.first, b.second) }
            }
        }
        return c.count { it == false }
    }

    fun calculateInSensor(cor: Map.Entry<Pair<Long, Long>, Long>, interval: Long, atLine: Long): LongRange? {
        val size = (cor.value - (cor.key.second - atLine).absoluteValue)
        if (size < 0) {
            return null
        } else {
            return (maxOf(cor.key.first - size, 0)..minOf(cor.key.first + size, interval))
        }
    }

    fun solve2(line: Long): Long {
        val cor = parse()
        val mans = cor.associate { it.first to manhattan(it.first, it.second) }
        var (x, y) = Pair<Long, Long>(0, 0)
        run runn@{
            (0..line).forEach loop@{ l ->
                val ints: List<LongRange> = mans.mapNotNull {
                    calculateInSensor(it, line, atLine = l)
                }.sortedBy { it.first }
                val global = ints.reduce { acc, longRange ->
                    if (longRange.first - 1 in acc) {
                        (minOf(longRange.first, acc.first)..maxOf(longRange.last, acc.last))
                    } else {
                        (longRange.first..maxOf(longRange.last, acc.last))
                    }
                }
                if (global.first != 0L) {
                    x = global.first - 1L
                    y = l
                    return@runn
                }
            }
        }
        return x * 4000000L + y

    }

    fun parse(): MutableList<Pair<Pair<Long, Long>, Pair<Long, Long>>> {
        val sensors = mutableListOf<Pair<Long, Long>>()
        val beacons = mutableListOf<Pair<Long, Long>>()
        val co = mutableListOf<Pair<Pair<Long, Long>, Pair<Long, Long>>>()
        input.split("\n").forEach {
            val (s, b) = it.split(":")
            val (sX, sY) = s.split(",").map { it.split("=").last().toLong() }
            val p1 = Pair(sX, sY)
            sensors.add(p1) // sensors
            val (bX, bY) = b.split(",").map { it.split("=").last().toLong() }
            val p2 = Pair(bX, bY)
            beacons.add(p2) // beacons
            co.add(Pair(p1, p2))
        }
        return co
    }
}

fun main() {
    val d = Day15()
    println(d.solve(2000000L))
    println(d.solve2(4000000L))
}