package helpers

import kotlin.math.pow
import kotlin.math.sqrt

typealias Line = Pair<Point, Point>

fun Line.distance(): Double =
    sqrt((first.first - second.first).toDouble().pow(2) + (first.second - second.second).toDouble().pow(2))