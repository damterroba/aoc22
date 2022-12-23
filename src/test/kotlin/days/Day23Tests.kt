package days

import kotlin.test.Test
import kotlin.test.assertEquals

class Day23Tests {
    val d = Day23()
    @Test
    fun t(){
        assertEquals(110, d.part1())
        assertEquals(20, d.part2())
    }
}