package days

import kotlin.test.Test
import kotlin.test.assertEquals

class Day18Tests {
    val d= Day18()
    @Test
    fun t(){
        assertEquals(64, d.part1())
        assertEquals(58, d.part2())
    }
}