package days

import kotlin.test.Test
import kotlin.test.assertEquals



class Day12Tests {
    val d = Day12()

    @Test
    fun t() {
        assertEquals(31, d.part1())
        assertEquals(29, d.part2())
    }
}