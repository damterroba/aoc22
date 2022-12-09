package days

import helpers.Point
import kotlin.math.absoluteValue

class Day9 : Day() {
    val directions = mapOf(
        "R" to Point(1, 0),
        "U" to Point(0, 1),
        "L" to Point(-1, 0),
        "D" to Point(0, -1)
    )

    override fun part1(): Any {
        return solve()
    }

    override fun part2(): Any {
        return solve(10)
    }
    
    fun solve(size: Int = 2): Int {
        val seenPositions = mutableListOf<Point>()
        val rope = MutableList(size) { mutableMapOf('x' to 0, 'y' to 0) }
        input.split("\n").forEach {
            val (direction, count) = it.split(" ")
            repeat(count.toInt()) {
                rope[0]['x'] = rope[0]['x']!! + directions.get(direction)!!.first
                rope[0]['y'] = rope[0]['y']!! + directions.get(direction)!!.second
                (1 until rope.size).forEach {ind ->
                    val deltaX = rope[ind]['x']!!-rope[ind-1]['x']!!
                    val deltaY = rope[ind]['y']!!-rope[ind-1]['y']!!
                    val dist = maxOf(deltaX.absoluteValue, deltaY.absoluteValue)
                    if(dist>1){// should move
                        val newX = minOf(1, deltaX.absoluteValue ) * (if(deltaX < 0) 1 else if(deltaX == 0) 0 else -1)
                        val newY = minOf(1, deltaY.absoluteValue ) * (if(deltaY < 0) 1 else if(deltaY == 0) 0 else -1)
                        rope[ind]['x'] = rope[ind]['x']!! + newX
                        rope[ind]['y'] = rope[ind]['y']!! + newY

                    }
                }
                seenPositions.add(Point(rope.last()['x']!!, rope.last()['y']!!))
            }
        }
        return seenPositions.toSet().size
    }
}

fun main(){
    val d = Day9()
    println(d.part1())
    println(d.part2())
}

