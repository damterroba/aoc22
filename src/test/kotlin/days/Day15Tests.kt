package days

import kotlin.test.Test
import kotlin.test.assertEquals

class Day15Tests {
    val d = Day15()
    @Test
    fun t(){
        assertEquals(26, d.solve(10L))
        assertEquals(56000011L, d.solve2(20L))
    }
    
}