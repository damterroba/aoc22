package days

import kotlin.test.Test
import kotlin.test.assertEquals

class Day10Tests {
    val d = Day10()
    
    @Test
    fun t(){
        assertEquals(13140, d.part1())
        d.part2()
    }
}