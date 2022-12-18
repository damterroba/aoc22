package days


data class Pos(val x: Int, val y: Int, val z: Int) {
    companion object {
        fun from(l: List<Int>): Pos {
            return Pos(l[0], l[1], l[2])
        }
    }

    fun neighbors(): Set<Pos> {
        return setOf<Pos>(
            Pos(x, y, z - 1),
            Pos(x, y, z + 1),
            Pos(x, y - 1, z),
            Pos(x, y + 1, z),
            Pos(x + 1, y, z),
            Pos(x - 1, y, z)
        )
    }

}

class Day18 : Day() {
    override fun part1(): Int {
        val pos = input.split("\n").map { Pos.from(it.split(",").map { it.toInt() }) }
        return surf(pos)
    }

    fun surf(p: List<Pos>): Int {
        return p.map { it.neighbors().count { it in p } }.map { 6 - it }.sum()
    }

    override fun part2(): Int {
        val pos = input.split("\n").map { Pos.from(it.split(",").map { it.toInt() }) }
        val maxX = pos.maxOf { it.x +1}
        val maxY = pos.maxOf { it.y +1 }
        val maxZ = pos.maxOf { it.z +1 }
        val minX = pos.minOf { it.x -1 }
        val minY = pos.minOf { it.y -1 }
        val minZ = pos.minOf { it.z -1 }

        fun bfs(point: Pos): MutableSet<Pos> {
            val queue = ArrayDeque<Pos>()
            val visited = mutableSetOf<Pos>()
            queue.add(point)
            while (queue.isNotEmpty()) {
                var cPoint = queue.removeFirst()
                if (cPoint in pos || cPoint in visited || cPoint.x !in (minX..maxX) || cPoint.y !in (minY..maxY) || cPoint.z !in (minZ..maxZ)) {
                    continue
                }
                visited.add(cPoint)
                val neighbors = cPoint.neighbors()
                neighbors.forEach {
                    queue.add(it)
                }
                
            }
            return visited
        }
        
        var c= 0
        val outputs = bfs(Pos(1, 1, 1))
        pos.forEach { p ->
            p.neighbors().forEach{
                if( it !in pos){
                    if(it in outputs){
                        c++
                    }
                }
            }
        }
        return c
    }
}

fun main() {
    val d = Day18()
    println(d.part1())
    println(d.part2())
}