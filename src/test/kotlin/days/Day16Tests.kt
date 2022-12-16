package days

import kotlin.test.Test
import kotlin.test.assertEquals

class Day16Tests {
    val d = Day16()
    @Test
    fun t(){
        assertEquals(1651, d.part1())
        assertEquals(1707, d.part2())
    }
}