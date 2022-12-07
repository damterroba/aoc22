package days

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day7Tests {
    val d= Day7()
    
    @Test
    fun t(){
        assertEquals(95437, d.part1())
        assertEquals(24933642, d.part2())
    }
}