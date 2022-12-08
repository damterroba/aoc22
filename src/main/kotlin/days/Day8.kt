package days

class Day8 : Day() {
    var forest: List<List<Int>>
    init{
        forest = input.split("\n").map { it.map { d -> d.digitToInt() } }
    }
    override fun part1(): Any {
        return bestTrees().flatten().count { it }
    }

    override fun part2(): Any {
        return forest.mapIndexed { y, r ->
            r.mapIndexed { x, _ ->
                val c = forest[y][x]
                var left = 0
                var right = 0
                var up = 0
                var down=0
                if(x>0){
                    run breaking@{
                        (x - 1 downTo 0).forEach {
                            left++
                            if (forest[y][it] >= c) return@breaking
                        }
                    }
                }
                if(x < forest[y].size-1){
                    run breaking@{
                        (x+1 until forest[y].size).forEach{
                           right++
                            if (forest[y][it] >= c) return@breaking
                        }
                    }
                }
                if(y >0){
                    run breaking@{
                        (y-1 downTo 0).forEach {
                            up++
                            if (forest[it][x] >= c) return@breaking
                        }
                    }
                }
                if(y < forest.size-1){
                    run breaking@{
                        (y+1 until forest.size).forEach{
                            down++
                            if (forest[it][x] >= c) return@breaking
                        }
                    }
                }
                up*left*down*right
            }
        }.flatten().max()
    }
    
    fun bestTrees(): List<List<Boolean>> {
        return (0..forest.size - 1).map { y ->
            (0 until forest[0].size).map { x ->
                val left = if (x > 0) forest[y][x] > (0 until x).map { forest.getOrNull(y)?.getOrElse(it) { -1 } ?: -1 }
                    .max() else true
                val up = if (y > 0) forest[y][x] > (0 until y).map { forest.getOrNull(it)?.getOrElse(x) { -1 } ?: -1 }
                    .max() else true
                val down = if (y < forest.size - 1) forest[y][x] > (y + 1 until forest.size).map {
                    forest.getOrNull(it)?.getOrElse(x) { -1 } ?: -1
                }.max() else true
                val right = if (x < forest[y].size - 1) forest[y][x] > (x + 1 until forest[y].size).map {
                    forest.getOrNull(y)?.getOrElse(it) { -1 } ?: -1
                }.max() else true
                left || up || right || down
            }
        }
    }
}

fun main() {
    val d = Day8()
    println(d.part1())
    println(d.part2())
}