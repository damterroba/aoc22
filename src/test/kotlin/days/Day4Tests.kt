package days

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day4Tests {
    val d = Day4()
    @Test
    fun part1(){
        assertEquals(2, d.part1())
        assertEquals(4, d.part2())
    }
}