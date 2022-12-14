package days


import helpers.Point

fun List<List<Char>>.elevationAt(p: Point): Int {
    return when (this[p.second][p.first]) {
        'S' -> 'a'.code
        'E' -> 'z'.code
        else -> this[p.second][p.first].code
    }
}

class Day12 : Day() {
    var grid: List<List<Char>>
    init{
        grid = input.split("\n").map { it.map { c -> c } }
    }
    override fun part1(): Any {
        val start = grid.mapIndexed { y, chars ->
            chars.mapIndexedNotNull { x, c ->
                if (c == 'S') {
                    Pair(x, y)
                } else {
                    null
                }
            }
        }.flatten().first()
        val end = grid.mapIndexed { y, chars ->
            chars.mapIndexedNotNull { x, c ->
                if (c == 'E') {
                    Pair(x, y)
                } else {
                    null
                }
            }
        }.flatten().first()
        val sols = solve(start)
        return sols.get(end)!!
    }

    override fun part2(): Any {
        val starts = grid.mapIndexed{ y, chars -> 
            chars.mapIndexedNotNull{ x, c ->
                if(c=='a'){
                    Pair(x,y)
                }else{
                    null
                }
            }
        }.flatten()
        val end = grid.mapIndexed { y, chars ->
            chars.mapIndexedNotNull { x, c ->
                if (c == 'E') {
                    Pair(x, y)
                } else {
                    null
                }
            }
        }.flatten().first() 
        return starts.map { solve(it) }.filter { it.containsKey(end) }.minOf { it.get(end)!! }
    }

    fun solve(start: Point): Map<Point,Int> {
        fun getNeighbors(point: Point): List<Point> {
            val (x, y) = point
            return listOf(
                Point(x + 1, y),
                Point(x - 1, y),
                Point(x, y + 1),
                Point(x, y - 1)
            ).filter { it.first in (grid[0].indices) && it.second in grid.indices }
        }
        
        val queue = ArrayDeque<Point>()
        queue.add(start)
        val visited = mutableMapOf<Point, Int>()
        visited.put(start, 0)
        while (!queue.isEmpty()) {
            val current = queue.removeFirst()
            for (neighbor in getNeighbors(current)) {
                if ((neighbor !in visited.keys || visited.get(current)!! + 1 < visited.get(neighbor)!!) && (
                            grid.elevationAt(neighbor) - grid.elevationAt(current) <= 1
                            )
                ) {
                    visited.putIfAbsent(neighbor, Int.MAX_VALUE)
                    visited.replace(neighbor, visited.get(current)!! +1)
                    queue.add(neighbor)
                }
            }
        }
        return visited
    }
}

fun main() {
    val d = Day12()
    println(d.part1())
    println(d.part2())
}


