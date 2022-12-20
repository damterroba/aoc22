package days

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day20Tests {
    val d = Day20()
    
    @Test
    fun t(){
        assertEquals(3, d.part1())
        assertEquals(1623178306L, d.part2())
    } 
}