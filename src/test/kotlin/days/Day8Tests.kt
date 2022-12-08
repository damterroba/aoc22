package days

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day8Tests {
    val d= Day8()
    
    @Test
    fun t(){
        assertEquals(21, d.part1())
        assertEquals(8, d.part2())
    }
}