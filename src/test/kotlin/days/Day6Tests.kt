package days

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day6Tests {
    val d = Day6()
    @Test
    fun test(){
        assertEquals(5, d.part1())
    }
}