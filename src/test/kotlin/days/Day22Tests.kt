package days

import kotlin.test.Test
import kotlin.test.assertEquals

class Day22Tests {
    val d = Day22()
    
    @Test
    fun t(){
        assertEquals(6032, d.part1())
    }
}