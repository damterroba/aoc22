package days

import kotlin.test.Test
import kotlin.test.assertEquals

class Day13Tests {
    val d = Day13()
    
    @Test
    fun t(){
        assertEquals(13, d.part1())
        assertEquals(140, d.part2())
    }
}