package days

import kotlin.test.Test
import kotlin.test.assertEquals

class Day14Tests {
    val d = Day14()
    @Test
    fun t(){
        assertEquals(24, d.part1())
        assertEquals(93, d.part2())
    }
}