package helpers

typealias Point = Pair<Int, Int>

fun Point.adj(): Set<Point> = (-1..1 step 2).flatMap {
    listOf(Point(this.first + it, this.second), Point(this.first, this.second + it))
}.toSet()

fun Point.allAdj(): Set<Point> = (-1..1 step 2).flatMap { x ->
    (-1..1 step 2).map { y -> Point(this.first + x, this.second + y) }
}.toSet() + adj()

fun Point.belongsTo(line: Line): Boolean {
    return (first - line.first.first) * (line.second.second - line.first.second) - (second-line.first.second)*(line.second.first - line.first.first) == 0
}